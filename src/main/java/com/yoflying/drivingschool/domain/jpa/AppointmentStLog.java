package com.yoflying.drivingschool.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by arvin on 2017/3/27.
 */
@Table(name = "coachStudentLog")
public class AppointmentStLog {
    @Id
    private Long id;

    @Column(name = "dsId")
    private Long dsId;

    @Column(name = "coachId")
    private Long coachId;

    @Column(name = "studentId")
    private Long studentId;

    @Column(name = "course")
    private Integer testCourse;

    @Column(name = "testAddress")
    private String testAddress;

    @Column(name = "appointmentDate")
    private Timestamp appointmentDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "createTime")
    private Timestamp createTime ;

    @Column(name = "modifyTime")
    private Timestamp modifyTime ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDsId() {
        return dsId;
    }

    public void setDsId(Long dsId) {
        this.dsId = dsId;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getTestCourse() {
        return testCourse;
    }

    public void setTestCourse(Integer testCourse) {
        this.testCourse = testCourse;
    }

    public String getTestAddress() {
        return testAddress;
    }

    public void setTestAddress(String testAddress) {
        this.testAddress = testAddress;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
/*
 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dsId` bigint(20) NOT NULL COMMENT '驾校id',
  `coachId` bigint(20) NOT NULL COMMENT '教练id',
  `studentId` bigint(20) NOT NULL COMMENT '学员id',
  `appointmentDate` datetime DEFAULT NULL COMMENT '约车',
  `course` int(11) DEFAULT NULL COMMENT '课程1为科目一 2 为科目二 以此类推',
  `testAddress` varchar(250) NOT NULL COMMENT '练车地点',
  `status` int(11) DEFAULT NULL COMMENT '状态 1为正常 2为过期',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
 */