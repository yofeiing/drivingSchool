package com.yoflying.drivingschool.domain.service;

import com.yoflying.drivingschool.domain.jpa.StudentsDetail;

/**
 * Created by arvin on 2017/2/23.
 */
public interface StudentsDetailService {

    /**
     * 保存学员详情信息
     * @param studentsDetail
     * @return
     */
    int saveStudentsDetail(StudentsDetail studentsDetail);

}
