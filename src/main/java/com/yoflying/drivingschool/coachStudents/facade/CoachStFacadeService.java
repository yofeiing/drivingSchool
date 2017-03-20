package com.yoflying.drivingschool.coachStudents.facade;

import com.yoflying.drivingschool.coachStudents.model.StudentModel;
import com.yoflying.drivingschool.domain.jpa.AppointmentSt;
import com.yoflying.drivingschool.domain.model.CoachStudentUser;
import com.yoflying.drivingschool.entity.DSInfoEntity;

import java.util.List;

/**
 * Created by arvin on 2017/3/20.
 */
public interface CoachStFacadeService {
    List<AppointmentSt> getAppointmentInfo(long dsId, long coachId, int testCoures);
    DSInfoEntity getDsInfo(long dsId);
    List<AppointmentSt> getAppointmentInfo2(long dsId, long coachId);
    CoachStudentUser getCoach(long coachId);
    StudentModel getStudentModel(long dsId, long caachId);
    int appointmentDriving(long id, long studentsId);
}