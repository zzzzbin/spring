package com.example.demo.domain.user.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_salary")
public class Salary {
    //    private String userId;
    //    private String yearMonths;
    @EmbeddedId
    private SalaryKey salaryKey;
    private Integer salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "userId")
    private MUser user;
}
