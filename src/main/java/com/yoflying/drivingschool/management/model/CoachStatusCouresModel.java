package com.yoflying.drivingschool.management.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by liqiang on 16/12/18.
 */
public class CoachStatusCouresModel {

    @NotNull(message = "学生ID不能为空")
    private Long studentsId;

    @NotNull(message = "教练ID不能为空")
    private Long coachId;

    @NotNull(message = "当前学生科目不能为空")
    private Integer course;

    public Long getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(Long studentsId) {
        this.studentsId = studentsId;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }
}
