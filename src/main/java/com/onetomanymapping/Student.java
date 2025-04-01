package com.onetomanymapping;


import jakarta.persistence.*;
@Entity
@Table(name ="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  name;

    private String school;

    @ManyToOne()
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getSchool(){
        return school;
    }

    public void setSchool(String school){
        this.school= school;
    }

    public Teacher getTeacher(){
        return teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

}
