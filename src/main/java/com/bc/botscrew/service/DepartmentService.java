package com.bc.botscrew.service;

import com.bc.botscrew.dao.DepartmentRepo;
import com.bc.botscrew.entities.Department;
import com.bc.botscrew.entities.Lector;
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
        Department department = departmentRepository.findByName(departmentName);
        if(department != null && department.getHead() != null){
            return department.getHead().getName();
        }else {
            throw new NoSuchDepartmentException("Department was not found");
        }
    }

    public Map<String, Integer> getDepartmentStatistics(String departmentName) {
        Map<String, Integer> statistics = new HashMap<>();
        Department department = departmentRepository.findByName(departmentName);

        if (department != null) {
            List<Lector> lectors = new ArrayList<>(department.getLectors());
            int assistantsCount = 0;
            int associateProfessorsCount = 0;
            int professorsCount = 0;

            for (Lector lector : lectors) {

                switch (lector.getDegree().getName()) {
                    case ASSISTANT:
                        assistantsCount++;
                        break;
                    case ASSOCIATE_PROFESSOR:
                        associateProfessorsCount++;
                        break;
                    case PROFESSOR:
                        professorsCount++;
                        break;
                }
            }

            statistics.put(ASSISTANT, assistantsCount);
            statistics.put(ASSOCIATE_PROFESSOR, associateProfessorsCount);
            statistics.put(PROFESSOR, professorsCount);
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
        Department department = departmentRepository.findByName(departmentName);
        if(department != null){
            return department.getLectors().size();
        }else {
            throw new NoSuchDepartmentException("Department was not found");
        }
    }
}
