package com.cruddemo.crudapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoId;
    @Column(name = "todoDescription")
    private String todoDescription;
    @Column(name = "isCompleted")
    private int isCompleted;

    @Column
    private int day;

    @Column
    private int week;
}
