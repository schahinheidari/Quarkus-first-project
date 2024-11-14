package org.example.model;

import jakarta.persistence.*;
import org.example.enums.AcademicRank;

@Entity
public class Professor extends User{

    @Column(unique = true, nullable = false)
    private int code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcademicRank academicRank;
}
