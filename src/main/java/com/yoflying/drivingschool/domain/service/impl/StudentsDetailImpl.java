package com.yoflying.drivingschool.domain.service.impl;

import com.yoflying.drivingschool.domain.dao.StudentsDetailMapper;
import com.yoflying.drivingschool.domain.jpa.StudentsDetail;
import com.yoflying.drivingschool.domain.service.StudentsDetailService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by arvin on 2017/2/23.
 */
@Repository
public class StudentsDetailImpl implements StudentsDetailService{
    @Resource
    StudentsDetailMapper studentsDetailMapper;

    public int saveStudentsDetail(StudentsDetail studentsDetail) {
        return studentsDetailMapper.insert(studentsDetail);
    }
}
