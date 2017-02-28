package com.yoflying.drivingschool.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by arvin on 2017/2/28.
 */
@Table(name = "test_data")
public class TestData {
    @Id
    private Long id;
    @Column(name = "type")
    private Integer type;
    @Column(name = "page")
    private Integer page;

    @Column(name = "data")
    private String data;
    @Column(name = "createTime")
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", type=" + type +
                ", page=" + page +
                ", data='" + data + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
