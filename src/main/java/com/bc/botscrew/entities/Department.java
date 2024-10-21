package com.bc.botscrew.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Lector_in_Department",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "lector_id")
    )
    private Set<Lector> lectors = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "head_lector_id")
    private Lector head;

    public Department() {
    }

    public Department(Long id, String name, Set<Lector> lectors, Lector head) {
        this.id = id;
        this.name = name;
        this.lectors = lectors;
        this.head = head;
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

    public Set<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(Set<Lector> lectors) {
        this.lectors = lectors;
    }

    public Lector getHead() {
        return head;
    }

    public void setHead(Lector head) {
        this.head = head;
    }
}
