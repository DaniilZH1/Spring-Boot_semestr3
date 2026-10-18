package MySpringBoot2Dbase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15)
    private String name;

    @Column(length = 25)
    private String surname;

    @Column(length = 20)
    private String faculty;

    private Integer age;

    public Student() {
    }

    public Student(String name, String surname, String faculty, Integer age) {
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.age = age;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}
