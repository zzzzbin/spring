package com.example.demo.domain.user.model;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "m_user")
public class MUser {
    @Id
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Integer departmentId;
    private String role;
    @ManyToOne(optional = true) //allow null => left join , optional specifies how to join(left, inner)
    @JoinColumn(insertable = false, updatable = false, name = "departmentId")
    //department is not included in inserta and update user
    private Department department; //owner side

    @OneToMany(mappedBy = "user")
    private List<Salary> salaryList;

    //creating a CSV string
    public String toCsv() {
        String genderStr = null;
        if (gender == 1) {
            genderStr = "Male";
        } else {
            genderStr = "Female";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String csv = userId + ", " + userName + ", " + sdf.format(birthday)
                + ", " + age + ", " + genderStr + "\r\n";
        return csv;
    }
}
