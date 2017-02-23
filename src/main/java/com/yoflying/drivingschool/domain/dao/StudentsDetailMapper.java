package com.yoflying.drivingschool.domain.dao;

import com.yoflying.drivingschool.domain.jpa.StudentsDetail;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by arvin on 2017/2/23.
 */
@MapperScan
public interface StudentsDetailMapper extends Mapper<StudentsDetail> {

}
