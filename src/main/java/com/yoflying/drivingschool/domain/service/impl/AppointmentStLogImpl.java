package com.yoflying.drivingschool.domain.service.impl;

import com.yoflying.drivingschool.domain.dao.AppointmentLogMapper;
import com.yoflying.drivingschool.domain.jpa.AppointmentStLog;
import com.yoflying.drivingschool.domain.service.AppointmentStLogService;
import com.yoflying.drivingschool.domain.service.AppointmentStService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * Created by arvin on 2017/3/27.
 */
@Repository
public class AppointmentStLogImpl implements AppointmentStLogService{

    @Resource
    AppointmentLogMapper appointmentLogMapper;

    @Override
    public List<AppointmentStLog> findAppointmentStLogbyDsIDandStId(Long dsId, Long stId) {
        AppointmentStLog appointment = new AppointmentStLog();
        appointment.setDsId(dsId);
        appointment.setStudentId(stId);
        return appointmentLogMapper.select(appointment);
    }
}
