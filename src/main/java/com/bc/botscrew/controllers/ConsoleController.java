package com.bc.botscrew.controllers;

import com.bc.botscrew.handlers.NoSuchDepartmentException;
import com.bc.botscrew.service.DepartmentService;
import com.bc.botscrew.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
public class ConsoleController implements CommandLineRunner {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LectorService lectorService;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the University Management Console!");
        System.out.println("Type 'exit' to quit the application.");
        System.out.println("Type '1 {depName}' to show head of Department e.g.");
        System.out.println("Available commands:");
        System.out.println("1. Who is head of department {department_name}");
        System.out.println("2. Show {department_name} statistics");
        System.out.println("3. Show average salary for department {department_name}");
        System.out.println("4. Show count of employees for {department_name}");
        System.out.println("5. Global search by {template}");

        System.out.flush();

        while (true) {
            System.out.print("\nEnter your command: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            }

            handleInput(input);
        }
    }

    private void handleInput(String input) {
        if(!input.contains(" ")){
            System.out.println("Unknown command. Please try again.");
            return;
        }
        String[] parts = input.split(" ");
        String commandId = parts[0];
        String inputText = parts[1];

        switch (commandId) {
            case "1":
                if (parts.length == 2) {
                    String head;
                    try {
                        head = departmentService.getDepartmentHead(inputText);
                    }catch (NoSuchDepartmentException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Head of " + inputText + " department is " + head);
                } else {
                    System.out.println("Invalid command format. Use: 1 {department_name}");
                }
                break;

            case "2":
                if (parts.length == 2) {
                    Map<String, Integer> stats;
                    try {
                        stats = departmentService.getDepartmentStatistics(inputText);
                    }catch (NoSuchDepartmentException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Assistants: " + stats.get(DepartmentService.ASSISTANT) +
                            ", Associate Professors: " + stats.get(DepartmentService.ASSOCIATE_PROFESSOR) +
                            ", Professors: " + stats.get(DepartmentService.PROFESSOR));
                } else {
                    System.out.println("Invalid command format. Use: 2 {department_name}");
                }
                break;

            case "3":
                if (parts.length == 2) {
                    double averageSalary;
                    try {
                        averageSalary = departmentService.getAverageSalary(inputText);
                    }catch (NoSuchDepartmentException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("The average salary of " + inputText + " is " + averageSalary);
                } else {
                    System.out.println("Invalid command format. Use: 3 {department_name}");
                }
                break;

            case "4":
                if (parts.length == 2) {
                    int count;
                    try {
                        count = departmentService.getEmployeeCount(inputText);
                    }catch (NoSuchDepartmentException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("The count of employees for " + inputText + " is " + count);
                } else {
                    System.out.println("Invalid command format. Use: 4 {department_name}");
                }
                break;

            case "5":
                if (parts.length == 2) {
                    var results = lectorService.globalSearch(inputText);
                    if(results.isEmpty()){
                        System.out.println("No results");
                        break;
                    }
                    System.out.println("Search results for '" + inputText + "':");
                    results.forEach(lector -> System.out.println("- " + lector.getName()));
                } else {
                    System.out.println("Invalid command format. Use: 5 {template}");
                }
                break;

            default:
                System.out.println("Unknown command. Please try again.");
        }
    }
}

