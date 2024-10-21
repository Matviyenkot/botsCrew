package com.bc.botscrew.service;

import com.bc.botscrew.dao.DepartmentRepo;
import com.bc.botscrew.dto.DepartmentStatisticsDTO;
import com.bc.botscrew.entities.Department;
import com.bc.botscrew.handlers.NoSuchDepartmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {

    public static final String ASSISTANT = "assistant";
    public static final String ASSOCIATE_PROFESSOR = "associate professor";
    public static final String PROFESSOR = "professor";

    @Autowired
    private DepartmentRepo departmentRepository;

    public String getDepartmentHead(String departmentName) {
        Department department = departmentRepository.findByNameIgnoringCase(departmentName);
        if(department != null && department.getHead() != null){
            return department.getHead().getName();
        }else {
            throw new NoSuchDepartmentException("Department was not found");
        }
    }

    public Map<String, Long> getDepartmentStatistics(String departmentName) {
        Map<String, Long> statistics = new HashMap<>();

        if (departmentRepository.existsByName(departmentName)) {
            DepartmentStatisticsDTO departmentStatistics = departmentRepository.findDepartmentStatistics(departmentName);
            statistics.put(ASSISTANT, departmentStatistics.getAssistants());
            statistics.put(ASSOCIATE_PROFESSOR, departmentStatistics.getAssociateProfessors());
            statistics.put(PROFESSOR, departmentStatistics.getProfessors());
        }else {
            throw new NoSuchDepartmentException("Department was not found");
        }
        return statistics;
    }

    public double getAverageSalary(String departmentName) {
        Double res = departmentRepository.findAverageSalaryByDepartmentName(departmentName);
        if(res != null){
            return res;
        }else {
            throw new NoSuchDepartmentException("Department was not found");
        }
    }

    public int getEmployeeCount(String departmentName) {
        Department department = departmentRepository.findByNameIgnoringCase(departmentName);
        if(department != null){
            return department.getLectors().size();
        }else {
            throw new NoSuchDepartmentException("Department was not found");
        }
    }
}
