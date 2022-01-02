package com.example.demo.domain.user.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="m_department")
public class Department {
    @Id
    private Integer departmentId;
    private String departmentName;

    /** CSV string creation. */
    public String toCsv() {
        String csv = departmentId + ", " + departmentName + "\r\n"; return csv;
    }
}
