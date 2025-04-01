package com.onetomanymapping;

import com.onetomanymapping.JPAUtil;
import java.util.*;
import jakarta.persistence.EntityManager;
public class Main {
    public static void main(String args[]){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        Scanner sc = new Scanner(System.in);


                // ✅ Create Teacher
                Teacher teacher = new Teacher();
                teacher.setName("Mr. Sharma");

                // ✅ Create Students
                Student s1 = new Student();
                s1.setName("Aman");
                s1.setSchool("LPU");
                s1.setTeacher(teacher);

                Student s2 = new Student();
                s2.setName("Riya");
                s1.setSchool("LPU");
                s2.setTeacher(teacher);

                // ✅ Add students to teacher's list
                teacher.getStudents().add(s1);
                teacher.getStudents().add(s2);

                // ✅ Save teacher (students automatically saved due to CascadeType.ALL)
                em.persist(teacher);

                em.getTransaction().commit();

                // ✅ Fetch Teacher & Their Students
                Teacher fetchedTeacher = em.find(Teacher.class, teacher.getId());
                System.out.println("Teacher: " + fetchedTeacher.getName());
                for (Student student : fetchedTeacher.getStudents()) {
                    System.out.println("Student: " + student.getName());
                }




    }
}
