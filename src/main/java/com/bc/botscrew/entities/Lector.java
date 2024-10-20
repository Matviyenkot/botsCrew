package com.bc.botscrew.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Lector")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "salary", nullable = false)
    private double salary;

    @ManyToMany(mappedBy = "lectors")
    @JsonBackReference
    private Set<Department> departments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "degree_id", nullable = false)
    private Degrees degree;

    public Lector() {
    }

    public Lector(Long id, String name, double salary, Set<Department> departments, Degrees degree) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.departments = departments;
        this.degree = degree;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Degrees getDegree() {
        return degree;
    }

    public void setDegree(Degrees degree) {
        this.degree = degree;
    }
}
