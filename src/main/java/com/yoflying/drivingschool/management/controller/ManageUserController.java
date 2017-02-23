package com.yoflying.drivingschool.management.controller;

import com.yoflying.drivingschool.domain.jpa.StudentsDetail;
import com.yoflying.drivingschool.domain.model.*;
import com.yoflying.drivingschool.domain.service.StudentsDetailService;
import com.yoflying.drivingschool.entity.DSInfoEntity;
import com.yoflying.drivingschool.infrastructure.realm.RoleSign;
import com.yoflying.drivingschool.management.BaseManageControllet;
import com.yoflying.drivingschool.constdef.ErrorDef;
import com.yoflying.drivingschool.domain.service.ManageUserService;
import com.yoflying.drivingschool.management.facade.ManageServiceFacade;
import com.yoflying.drivingschool.management.model.CoachStatusCouresModel;
import com.yoflying.drivingschool.utils.json.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 后台管理页面Controller
 * Created by liqiang on 16/12/13.
 */
@Controller
@RequestMapping("/manage")
public class ManageUserController extends BaseManageControllet {
    private final Logger logger = LoggerFactory.getLogger(ManageUserController.class);

    @Autowired
    ManageUserService manageUserService;

    @Autowired
    ManageServiceFacade manageServiceFacade;

    @Autowired
    MessageSource messageSource;

    /**
     * 管理员页面登录页面
     *
     * @param map
     * @return
     */
    @RequestMapping("/login")
    public String login(ModelMap map) {

        //// TODO: 16/12/13 返回login页面
        ManageUser m = manageUserService.authentication("", "");
        map.put("test", "test");
        return "/manage/login.ftl";
    }

//  username  password  host Ip地址

    /**
     * 管理员用户登录 post 请求 可以携带IP地址
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<String> loginPost(UsernamePasswordToken token) {
        logger.info("manage" + token.getUsername() + "---------" + token.getHost());

        if (StringUtils.isEmpty(token.getUsername()) || StringUtils.isEmpty(token.getUsername())) {
            return new JsonResult<String>("用户密码不能为空", ErrorDef.USER_PASS_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            token.clear();
            return new JsonResult<String>("用户密码错误", ErrorDef.USER_PASS_ERROR);
        }
        return new JsonResult<String>("登录成功", ErrorDef.SUCCESS);
    }

    /**
     * 获取当前管理员信息
     *
     * @return
     */
    @RequestMapping("/manageInfo")
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    public JsonResult<ManageUser> manageInfo() {

        return new JsonResult<ManageUser>(ErrorDef.SUCCESS, "成功", getManageUser());
    }

//    @Autowired
//    ApporintmentTask apporintmentTask;
    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    StudentsDetailService studentsDetailService;

    @RequestMapping(value = "/index")
    @RequiresRoles(RoleSign.ADMIN)
    public String index(ModelMap map) {

        //分页demo 目前已经集成
//        PageHelper.startPage(1, peage);
//        List<User> users = userMapper.byListUserName(name);
//        long total  = ((Page) users).getTotal();
        ManageUser manageUser = getManageUser();

        map.put("online",sessionDAO.getActiveSessions().size());

        //i18n TEST
        map.put("i18n", messageSource.getMessage("UnauthorizedException.message", null, LocaleContextHolder.getLocale()));

//        apporintmentTask.appointmentTask();

        /**Test jpa */


        StudentsDetail sd = new StudentsDetail();
        sd.setUserId(1L);
        sd.setDsId(1L);
        sd.setArrearage(new BigDecimal("1000.15"));
        sd.setFeePayable(new BigDecimal("1000.15"));
        sd.setStatus(1);
        sd.setCreateTime(new Timestamp(System.currentTimeMillis()));
        studentsDetailService.saveStudentsDetail(sd);


        return "/manage/index.ftl";
    }

    /**
     * 超级管理员创建驾校管理员
     *
     * @param manageUser
     * @return
     */
    @RequestMapping(value = "/createManage", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult createManage(@RequestBody ManageUser manageUser) {
        int err = manageServiceFacade.createManage(manageUser);
        return new JsonResult<String>("创建管理员成功", err);
    }

    /**
     * 创建教练or 学员
     *
     * @param coachStudentUser
     * @return
     */
    @RequestMapping(value = "/createCoachSt", method = RequestMethod.POST)
    @RequiresRoles(RoleSign.ADMIN)
    @ResponseBody
    public JsonResult createCoachSt(@RequestBody @Valid CoachStudentUser coachStudentUser, BindingResult result) {

        if (result.hasErrors()) {
            return getErrors(result);
        }

        coachStudentUser.setDsId(getManageUser().getDsId());
        int err = manageServiceFacade.createCoachSt(coachStudentUser);
        int discern = coachStudentUser.getDiscern();
        if (discern == 1) {
            return new JsonResult<String>("创建教练成功", err);
        }
        return new JsonResult<String>("创建学员成功", err);
    }

    /**
     * 超级管理员创建驾校
     *
     * @param drivingSchool
     * @return
     */
    @RequestMapping(value = "/createDrivingSchool", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult createDrivingSchool(@RequestBody @Valid DrivingSchool drivingSchool) {
        int err = manageServiceFacade.createDrivingSchool(drivingSchool);

        return new JsonResult<String>("创建驾校成功", err);
    }


    /**
     * 教练请假申请
     *
     * @param dsLeave
     * @return
     */
    @RequestMapping(value = "/createLeave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)  //@RequiresRoles(value = { "admin", "advuser" }, logical = Logical.OR)
    public JsonResult createLeave(@RequestBody @Valid DsLeave dsLeave) {

        dsLeave.setDsId(getManageUser().getDsId());

        int err = manageServiceFacade.createLeave(dsLeave);

        if(err > 0)
             return new JsonResult<String>("教练请假成功", ErrorDef.SUCCESS);

        return new JsonResult<String>("教练请假失败", ErrorDef.FAILURE);
    }

    /**
     * 根据驾校查找驾校所有学生
     *
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/findStudentList")
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    public JsonResult findStudentbyDsIdList(Integer pageNum) {
        ManageUser manageUser = getManageUser();
        if (pageNum != null || pageNum < 1) {
            pageNum = 1;
        }
        return manageServiceFacade.findStudentbyDsIdList(manageUser.getDsId(), pageNum);
    }

    /**
     * discern 为1 对该驾校教练进行模糊搜索 为2 对学员进行模糊搜索
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/searchCoachStList")
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    public JsonResult searchCoachStList(Integer discern, String name) {

        if (StringUtils.isEmpty(name) || !(discern <= 2) || !(discern > 0)) {
            return new JsonResult("参数传递错误", ErrorDef.SUCCESS);
        }

        return manageServiceFacade.searchCoachStList(getManageUser().getDsId(), name, discern);
    }

    /**
     * 根据驾校id 查找驾校所有教练
     *
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/findCoachList")
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    public JsonResult findCoachbyDsIdList(Integer pageNum) {
        ManageUser manageUser = getManageUser();

        if (pageNum ==0) {
            pageNum = 1;
        }

        return manageServiceFacade.findCoachbyDsIdList(manageUser.getDsId(), pageNum);
    }


    /**
     * 根据驾校id 查找驾校所有教练请假情况
     *
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/coachLeaveList")
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    public JsonResult coachLeaveList(Integer pageNum) {
        ManageUser manageUser = getManageUser();
        if (pageNum == null) {
            pageNum = 1;
        }
        return manageServiceFacade.coachLeaveList(manageUser.getDsId(), pageNum);
    }

    /**
     * 管理员更改驾校配置设置
     *
     * @param dsSetting
     * @return
     */
    @RequestMapping(value = "/settingDrivingConfig", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    public JsonResult settingDrivingConfig(@RequestBody @Valid DSSetting dsSetting , BindingResult result) {

        if (result.hasErrors()) {
            return getErrors(result);
        }

        dsSetting.setDsId(getManageUser().getDsId());
        //{"time":[{"start":"","stop":""},{"start":"","stop":""}],"size":3}
        int err = manageServiceFacade.settingDrivingconfig(dsSetting);
        if (err > 0) {
            return new JsonResult("更改驾校配置设置成功", ErrorDef.SUCCESS);
        } else {
            return new JsonResult("更改驾校配置设置失败", ErrorDef.FAILURE);
        }
    }

    /**
     * 管理员手动提交预约信息
     *
     * @param entitys
     * @return
     */
    @RequestMapping(value = "/postAppointment", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    public JsonResult postAppointment(@RequestBody List<AppointmentSt> entitys , BindingResult result) {

        if (result.hasErrors()) {
            return getErrors(result);
        }



        return null;
    }


    /**
     * 更改学员绑定教练 or 更改学员当前科目
     *
     * @return
     */
    @RequestMapping(value = "/bindCSCUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    public JsonResult bindCoachorStatusCourseUpdate(@RequestBody @Valid CoachStatusCouresModel cscModel, BindingResult result) {

        if (result.hasErrors()) {
            return getErrors(result);
        }

        int err = manageServiceFacade.bindCoachorStatusCourseUpdate(getManageUser().getDsId(), cscModel);

        return new JsonResult("操作成功", err);
    }

    /**
     * 获取驾校基础信息
     *
     * @return
     */
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    @RequestMapping(value = "/getDSInfo")
    public JsonResult getDSInfo() {

        DSInfoEntity dsInfoEntity = manageServiceFacade.getDSInfo(getManageUser().getDsId());

        return new JsonResult<DSInfoEntity>(ErrorDef.SUCCESS, "查询成功", dsInfoEntity);
    }

    /**
     * 获取驾校教练课程情况 传入时间 则显示传入的时间 否则显示当天时间
     * @return
     */
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    @RequestMapping(value = "/dsAppointrentSt")
    public JsonResult dsAppointrentSt(String date) {

        List<AppointmentSt> appointmentStList = manageServiceFacade.dsAppointrentSt(getManageUser().getDsId(), date);

        return new JsonResult<List<AppointmentSt>> (ErrorDef.SUCCESS, "查询成功", appointmentStList);
    }

    /**
     * 根据驾校教练id 和 练习科目 查找练习场地信息
     * @param coachId
     * @param testCourse
     * @return
     */
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    @RequestMapping(value = "/getTestAddress")
    public JsonResult testAddress(Long coachId, Integer testCourse) {

        CoachTestaAddress coachTestaAddress = manageServiceFacade.getTestAddress(coachId, testCourse);

        return new JsonResult<CoachTestaAddress> (ErrorDef.SUCCESS, "查询成功", coachTestaAddress);
    }

    /**
     * 根据驾校教练id 和 练习科目 更新 or 保存 驾校练习场地信息
     * @param coachId
     * @param testCourse
     * @param testAddress
     * @return
     */
    @ResponseBody
    @RequiresRoles(RoleSign.ADMIN)
    @RequestMapping(value = "/saveTestAddress")
    public JsonResult saveTestAddress(Long coachId, Integer testCourse, String testAddress) {

        CoachTestaAddress coachTestaAddress = manageServiceFacade.getTestAddress(coachId, testCourse);

        int ret = 0;
        if (coachTestaAddress ==  null && !StringUtils.isEmpty(testAddress)) {
            CoachTestaAddress testAdd = new CoachTestaAddress();
            testAdd.setTestAddress(testAddress);
            testAdd.setUserId(coachId);
            testAdd.setTestCourse(testCourse);
            ret =  manageServiceFacade.saveTestAddress(testAdd);
        } else if (!StringUtils.isEmpty(testAddress)){
            ret = manageServiceFacade.updateTestAddress(coachTestaAddress.getId(), testAddress);
        }

        return ret > 0 ? new JsonResult<CoachTestaAddress> ("操作成功", ErrorDef.SUCCESS)
                :  new JsonResult<CoachTestaAddress> ("操作失败", ErrorDef.SUCCESS);
    }


}
