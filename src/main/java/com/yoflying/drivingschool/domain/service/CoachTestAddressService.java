package com.yoflying.drivingschool.domain.service;

import com.yoflying.drivingschool.domain.model.CoachTestaAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by arvin on 2016/12/23.
 */
public interface CoachTestAddressService {

    int insertCoachTestAddress(CoachTestaAddress coachTestaAddress);

    List<CoachTestaAddress> findCTAByUserId(Long userId);

    List<CoachTestaAddress> findCTAByUserIdAndCoures(Long userId, Integer testCourse);

    int updateCTAByUserIdAndCoures(Long id, String testAddress);
}
