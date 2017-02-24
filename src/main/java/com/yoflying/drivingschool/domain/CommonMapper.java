package com.yoflying.drivingschool.domain;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by arvin on 2017/2/24
 * 这个类仅用于继承获取上层方法
 *
 * 所有操作数据库的子类都需要继承这个类
 */
public interface CommonMapper <T> extends Mapper<T>, MySqlMapper<T> {
}
