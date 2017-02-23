package com.yoflying.drivingschool.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by arvin on 2017/2/23.
 */
@Table(name = "students_details")
public class StudentsDetail {
    @Id
    private Long id;
    @Column(name = "dsId")
    private Long dsId;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "feePayable")
    private BigDecimal feePayable;
    @Column(name = "arrearage")
    private BigDecimal arrearage;
    @Column(name = "status")
    private int status;
    @Column(name = "createTime")
    private Timestamp createTime;
    @Column(name = "modifyTime")
    private Timestamp modifyTime =  new Timestamp(System.currentTimeMillis());


    @Override
    public String toString() {
        return "StudentsDetail{" +
                "id=" + id +
                ", dsId=" + dsId +
                ", userId=" + userId +
                ", feePayable=" + feePayable +
                ", arrearage=" + arrearage +
                ", status=" + status +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getFeePayable() {
        return feePayable;
    }

    public void setFeePayable(BigDecimal feePayable) {
        this.feePayable = feePayable;
    }

    public BigDecimal getArrearage() {
        return arrearage;
    }

    public void setArrearage(BigDecimal arrearage) {
        this.arrearage = arrearage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
  `userId` bigint(20) NOT NULL COMMENT '学员id',
  `feePayable` decimal(15,1) DEFAULT NULL COMMENT '应交金额',
  `arrearage` decimal(15,1) DEFAULT NULL COMMENT '欠费金额',
  `status` int(11) DEFAULT NULL COMMENT '状态 1为正常 2为欠费',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
 */