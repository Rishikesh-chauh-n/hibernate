package com.onetomanymapping;

import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name="teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedby = "teacher", cascade = CascadeType.ALL)
    private List<Student> student = new ArrayList<>();

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public List<Student> getStudents(){
        return student;
    }
    public void setStudents(List<Student> student){
        this.student = student;
    }


}
