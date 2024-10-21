package com.bc.botscrew.dao;

import com.bc.botscrew.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    Department findByNameIgnoringCase(String name);

    @Query("SELECT AVG(l.salary) FROM Department d JOIN Lector l ON d.head.id = l.id WHERE LOWER(d.name) = LOWER(:name)")
    Double findAverageSalaryByDepartmentName(@Param("name") String name);

}
