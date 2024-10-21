package com.bc.botscrew.dto;

public class DepartmentStatisticsDTO {
    private Long assistants;
    private Long associateProfessors;
    private Long professors;

    public DepartmentStatisticsDTO(Long assistants, Long associateProfessors, Long professors) {
        this.assistants = assistants;
        this.associateProfessors = associateProfessors;
        this.professors = professors;
    }

    public Long getAssistants() {
        return assistants;
    }

    public Long getAssociateProfessors() {
        return associateProfessors;
    }

    public Long getProfessors() {
        return professors;
    }
}

