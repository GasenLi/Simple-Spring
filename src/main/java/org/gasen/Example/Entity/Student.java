package org.gasen.Example.Entity;

import java.util.Date;

public class Student {
    private String studentName;
    private Date birthday;
    private Class theClass;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Class getTheClass() {
        return theClass;
    }

    public void setTheClass(Class theClass) {
        this.theClass = theClass;
    }
}
