package org.example.model;

import jakarta.persistence.*;
import org.example.enums.AcademicLevel;
import org.example.enums.Branch;

@Entity
public class Student extends User {
    @Column(unique = true, nullable = false, updatable = false)
    private long stdNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcademicLevel academicLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Branch branch;

    public Student(long stdNumber, AcademicLevel academicLevel, Branch branch) {
        this.stdNumber = stdNumber;
        this.academicLevel = academicLevel;
        this.branch = branch;
    }
    public Student() {

    }

    public long getStdNumber() {
        return stdNumber;
    }

    public void setStdNumber(long stdNumber) {
        this.stdNumber = stdNumber;
    }

    public AcademicLevel getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
