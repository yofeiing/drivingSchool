package com.yoflying.drivingschool.management.facade;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yoflying.drivingschool.constdef.Const;
import com.yoflying.drivingschool.constdef.ErrorDef;
import com.yoflying.drivingschool.domain.model.*;
import com.yoflying.drivingschool.domain.service.*;
import com.yoflying.drivingschool.utils.json.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liqiang on 16/12/15.
 */
@Service
public class ManageServiceFacade {

    private final Logger logger = LoggerFactory.getLogger(ManageServiceFacade.class);

    @Autowired
    ManageUserService manageUserService;

    @Autowired
    CoachStudentService coachStudentService;

    @Autowired
    DrivingSchoolService drivingSchoolService;

    @Autowired
    DsLeaveService dsLeaveService;

    @Autowired
    DSSettingService dsSettingService;

    public int createManage(ManageUser manageUser) {

        manageUserService.insertManage(manageUser);

        return ErrorDef.SUCCESS;
    }

    public int createCoachSt(CoachStudentUser coachStudentUser) {

        coachStudentService.insertCoachStudentUser(coachStudentUser);

        return ErrorDef.SUCCESS;
    }

    public int createDrivingSchool(DrivingSchool drivingSchool) {

        drivingSchoolService.insertByDrivingSchool(drivingSchool);

        return ErrorDef.SUCCESS;
    }

    public int createLeave(DsLeave dsLeave) {

        dsLeaveService.insertDsLeave(dsLeave);

        return ErrorDef.SUCCESS;
    }

    public JsonResult<List<CoachStudentUser>> findStudentbyDsIdList(Long dsId, int pageNum) {

        PageHelper.startPage(pageNum, Const.DEF_PAGE_SIZA);
        List<CoachStudentUser> studentUsers = coachStudentService.findStByDsIdList(dsId);
        long total  = ((Page) studentUsers).getTotal();

        return new JsonResult<List<CoachStudentUser>>(ErrorDef.SUCCESS, "查询成功", pageNum, total, studentUsers);
    }

    public JsonResult<List<CoachStudentUser>> findCoachbyDsIdList(Long dsId, int pageNum) {

        PageHelper.startPage(pageNum, Const.DEF_PAGE_SIZA);
        List<CoachStudentUser> studentUsers = coachStudentService.findCoachByDsIdList(dsId);
        long total  = ((Page) studentUsers).getTotal();

        return new JsonResult<List<CoachStudentUser>>(ErrorDef.SUCCESS, "查询成功", pageNum, total, studentUsers);
    }

    public int settingDrivingconfig(DSSetting dsSetting) {

        dsSettingService.updateDssetting(dsSetting);

        return ErrorDef.SUCCESS;
    }
}
