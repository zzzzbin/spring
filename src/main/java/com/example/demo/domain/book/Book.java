package com.example.demo.domain.book;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book implements Serializable {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "auto generated id")
    private int bookId;
    @ApiModelProperty(value = "This is the book name")
    private String bookName;
    @ApiModelProperty(value = "This is the book price")
    private double price;
}
