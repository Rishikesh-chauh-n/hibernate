package com.createtable_through_class.com;




import jakarta.persistence.*;
        import java.util.*;

@Entity
public class StudentMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ElementCollection
    private List<String> skills = new ArrayList<>(); // ✅ Ensure this line exists

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; } // ✅ Ensure this setter exists
}
