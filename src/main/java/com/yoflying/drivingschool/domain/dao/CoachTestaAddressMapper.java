package com.yoflying.drivingschool.domain.dao;

import com.yoflying.drivingschool.domain.model.CoachTestaAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by arvin on 2016/12/23.
 */
@MapperScan
public interface CoachTestaAddressMapper {

    @Insert("INSERT INTO coach_test_address (userId,testCourse,testAddress)" +
            " VALUES(#{userId},#{testCourse},#{testAddress})")
    int insertCoachTestAddress(CoachTestaAddress coachTestaAddress);

    @Select("SELECT * FROM coach_test_address WHERE userId = #{userId}")
    List<CoachTestaAddress> findCTAByUserIdAndCoures(@Param("userId") Long userId);

    @Select("SELECT * FROM coach_test_address WHERE userId = #{userId} and testCourse = #{testCourse}")
    List<CoachTestaAddress> findCTAByUserIdAndCoures(@Param("userId") Long userId, @Param("testCourse") Integer testCourse);

    @Update("UPDATE coach_test_address SET testAddress = #{testAddress}"+
            ",modifyTime = NOW() WHERE id = #{id}")
    int updateCTAByUserIdAndCoures(@Param("id") Long id, @Param("testAddress") String testAddress);
}
