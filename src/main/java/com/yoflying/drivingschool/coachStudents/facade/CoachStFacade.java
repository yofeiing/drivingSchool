package com.yoflying.drivingschool.coachStudents.facade;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yoflying.drivingschool.coachStudents.model.StudentModel;
import com.yoflying.drivingschool.constdef.Const;
import com.yoflying.drivingschool.constdef.ErrorDef;
import com.yoflying.drivingschool.domain.jpa.AppointmentSt;
import com.yoflying.drivingschool.domain.jpa.AppointmentStLog;
import com.yoflying.drivingschool.domain.model.CoachStudentUser;
import com.yoflying.drivingschool.domain.model.DrivingSchool;
import com.yoflying.drivingschool.domain.model.DsLeave;
import com.yoflying.drivingschool.domain.service.AppointmentStLogService;
import com.yoflying.drivingschool.domain.service.AppointmentStService;
import com.yoflying.drivingschool.domain.service.CoachStudentService;
import com.yoflying.drivingschool.domain.service.DrivingSchoolService;
import com.yoflying.drivingschool.entity.DSInfoEntity;
import com.yoflying.drivingschool.utils.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiang on 16/12/18.
 */
@Service
public class CoachStFacade implements CoachStFacadeService{

    @Autowired
    AppointmentStService appointmentStService;

    @Autowired
    DrivingSchoolService drivingSchoolService;

    @Autowired
    CoachStudentService coachStudentService;

    @Autowired
    AppointmentStLogService appointmentStLogService;

    @Transactional
    public List<AppointmentSt> getAppointmentInfo(long dsId, long coachId, int testCoures) {
        List<AppointmentSt>  appointmentSts = appointmentStService.findAppointmentStbyCoachIdandCoures(dsId, coachId, testCoures);
        return appointmentSts;
    }

    public DSInfoEntity getDsInfo(long dsId) {

        DrivingSchool drivingSchool = drivingSchoolService.findByDrivingSchool(dsId);

        DSInfoEntity dsInfoEntity = new DSInfoEntity();
        dsInfoEntity.setDsName(drivingSchool.getDsName());
        dsInfoEntity.setDsLogo(drivingSchool.getLogo());
        dsInfoEntity.setAddress(drivingSchool.getDsAddress());

        return dsInfoEntity;
    }

    @Transactional
    public List<AppointmentSt> getAppointmentInfo2(long dsId, long coachId) {
        List<AppointmentSt>  appointmentSts = appointmentStService.findAppointmentStbyDsIDandCoachId(dsId, coachId);
        return appointmentSts;
    }

    public CoachStudentUser getCoach(long coachId) {
        return coachStudentService.findOneByCoachStID(coachId);
    }

    public StudentModel getStudentModel(long dsId, long caachId) {
        StudentModel studentModel = new StudentModel();
        DrivingSchool drivingSchool = drivingSchoolService.findByDrivingSchool(dsId);
        CoachStudentUser coach = getCoach(caachId);
        studentModel.setDsName(drivingSchool.getDsName());
        studentModel.setAnnouncement("测试消息----测试消息");
        studentModel.setComments("100%");
        studentModel.setDsLogo(drivingSchool.getLogo());
        studentModel.setLevel("神级");
        studentModel.setMyCoach(coach == null ? "未绑定" : coach.getName());
        studentModel.setMyCoachId(coach == null ? 0 :coach.getId());
        studentModel.setPhone(coach == null ? "未绑定" : coach.getPhone());
        return studentModel;
    }

    @Transactional
    public int appointmentDriving(long id, long studentsId) {

        AppointmentSt st =  appointmentStService.appointmentStbyStatusById(id);
        if (st != null) {
            JSONObject appointmentDate = (JSONObject) JSON.parse(st.getAppointmentDate());

            int size = appointmentDate.getInteger("size");
            List<Long> studentsIds;

            if (!StringUtils.isEmpty(st.getStudentsIds())) {
                studentsIds = (List<Long>) JSON.parse(st.getStudentsIds());
            } else {
                studentsIds = new ArrayList<>();
            }

            if (studentsIds.size() >= size) {
                return ErrorDef.FAILURE;
            } else {
                studentsIds.add(studentsId);
                int ret = appointmentStService.updateAppointmentStById(id, JSON.toJSON(studentsIds).toString());
                if (ret >= 0) {
                    return ret;
                }
            }
        }

        return ErrorDef.FAILURE;
    }

    @Override
    public List<AppointmentSt> futureAppointment(long dsId, long stId) {
        return appointmentStService.findAppointmentStbyDsIDandStIdAll(dsId, stId);
    }

    @Override
    public JsonResult historyAppointment(long dsId, long stId, int pageNum) {
        PageHelper.startPage(pageNum, Const.DEF_PAGE_SIZA);
        List<AppointmentStLog> datas = appointmentStLogService.findAppointmentStLogbyDsIDandStId(dsId, stId);
        long total  = ((Page) datas).getTotal();
        return new JsonResult(ErrorDef.SUCCESS, "查询成功", pageNum, total, datas);
    }
}
