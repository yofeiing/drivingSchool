package com.yoflying.drivingschool.management.facade;

import com.yoflying.drivingschool.domain.jpa.AppointmentSt;
import com.yoflying.drivingschool.domain.model.*;
import com.yoflying.drivingschool.entity.DSInfoEntity;
import com.yoflying.drivingschool.management.model.CoachStatusCouresModel;
import com.yoflying.drivingschool.utils.json.JsonResult;

import java.util.List;

/**
 * Created by arvin on 2017/3/23.
 */
public interface ManageService {
    int createManage(ManageUser manageUser);
    int createCoachSt(CoachStudentUser coachStudentUser);
    int createDrivingSchool(DrivingSchool drivingSchool);
    int createLeave(DsLeave dsLeave);
    JsonResult<List<CoachStudentUser>> findStudentbyDsIdList(Long dsId, int pageNum);
    JsonResult<List<DsLeave>> coachLeaveList(Long dsId, int pageNum);
    JsonResult<List<CoachStudentUser>> findCoachbyDsIdList(Long dsId, int pageNum);
    int settingDrivingconfig(DSSetting dsSetting);
    JsonResult searchCoachStList(Long dsId, String name, int discern);
    int bindCoachorStatusCourseUpdate(long dsId, CoachStatusCouresModel coachStatusCouresModel);
    DSInfoEntity getDSInfo(long dsid);
    List<AppointmentSt> dsAppointrentSt(long dsId, String date);
    CoachTestaAddress getTestAddress(Long coachId, Integer testCourse);
    int saveTestAddress(CoachTestaAddress coachTestaAddress);
    int updateTestAddress(Long id, String testAddress);
    int saveAppointmentSt(AppointmentSt st);
}
