package com.yoflying.drivingschool.domain.service;

import com.yoflying.drivingschool.domain.jpa.AppointmentStLog;

import java.util.List;

/**
 * Created by arvin on 2017/3/27.
 */
public interface AppointmentStLogService {
    /**
     * 根据驾校id 和学员id 查找历史约车信息
     * @param dsId
     * @param stId
     * @return
     */
    List<AppointmentStLog> findAppointmentStLogbyDsIDandStId(Long dsId, Long stId);
}
