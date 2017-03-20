package com.yoflying.drivingschool.coachStudents.controller;

import com.alibaba.fastjson.JSON;
import com.yoflying.drivingschool.coachStudents.BaseCsController;
import com.yoflying.drivingschool.coachStudents.facade.CoachStFacade;
import com.yoflying.drivingschool.coachStudents.facade.CoachStFacadeService;
import com.yoflying.drivingschool.constdef.ErrorDef;
import com.yoflying.drivingschool.domain.jpa.AppointmentSt;
import com.yoflying.drivingschool.domain.model.CoachStudentUser;
import com.yoflying.drivingschool.infrastructure.realm.RoleSign;
import com.yoflying.drivingschool.utils.json.JsonResult;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liqiang on 16/12/14.
 */
@RequestMapping("/coachstudent/coach")
@Controller
public class CoachController extends BaseCsController {

    @Autowired
    CoachStFacadeService coachStFacade;

    @RequestMapping("/index")
    @RequiresRoles(RoleSign.COACH)
    public String index(ModelMap map) {
        CoachStudentUser coach = getCoachStudentUser();

        map.put("dsInfo", JSON.toJSONString(coachStFacade.getDsInfo(coach.getDsId())));
        map.put("coachInfo", JSON.toJSONString(coach));
        map.put("appointment", JSON.toJSONString(coachStFacade.getAppointmentInfo2(coach.getDsId(), coach.getId())));

        return "/coachSt/coach.ftl";
    }
    /**
     * 教练获取当前约车信息
     * @return
     */
    @RequestMapping(value = "/getAppointment", method = RequestMethod.GET)
    @RequiresRoles(RoleSign.COACH)
    @ResponseBody
    public JsonResult getAppointment() {
        CoachStudentUser coach = getCoachStudentUser();

        List<AppointmentSt> appointmentSts = coachStFacade.getAppointmentInfo2(coach.getDsId(), coach.getId());
//        if (appointmentSts != null && appointmentSts.size() > 0)
        return new JsonResult<List<AppointmentSt>>(ErrorDef.SUCCESS, "返回数据", appointmentSts);
    }

}
