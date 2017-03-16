package com.yoflying.drivingschool.coachStudents.model;

/**
 * Created by waddw on 2017/3/16.
 */
public class AppointmentModel {
    private Long id;
    private Long studentsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(Long studentsId) {
        this.studentsId = studentsId;
    }
}
