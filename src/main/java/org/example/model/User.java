package org.example.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "USER", schema = "quarkusDB")
public class User {
    @Id
    private long id;
    private String name;
    private String family;
    private int age;

    @Column(unique = true, updatable = false)
    private long nationalCode;

    public User() {

    }
    public User(long id, String name, String family,int age, long nationalCode) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.age = age;
        this.nationalCode = nationalCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(long nationalCode) {
        this.nationalCode = nationalCode;
    }
}
