package com.bc.botscrew.dao;

import com.bc.botscrew.dto.DepartmentStatisticsDTO;
import com.bc.botscrew.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    Department findByNameIgnoringCase(String name);

    @Query("SELECT AVG(l.salary) FROM Department d JOIN Lector l ON d.head.id = l.id WHERE LOWER(d.name) = LOWER(:name)")
    Double findAverageSalaryByDepartmentName(@Param("name") String name);

    boolean existsByName(String name);

    @Query("SELECT new com.bc.botscrew.dto.DepartmentStatisticsDTO(" +
            "SUM(CASE WHEN l.degree.id = 1 THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN l.degree.id = 2 THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN l.degree.id = 3 THEN 1 ELSE 0 END)) " +
            "FROM Department d " +
            "JOIN d.lectors l " +
            "WHERE LOWER(d.name) = LOWER(:name)")
    DepartmentStatisticsDTO findDepartmentStatistics(@Param("name") String name);

}
