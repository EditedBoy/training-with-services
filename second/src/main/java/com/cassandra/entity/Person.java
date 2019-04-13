package com.cassandra.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("people_by_first_name")
public class Person implements Serializable {

    @PrimaryKey
    private PersonKey key;

    @Column("last_name")
    private String lastName;

    @Column
    private double salary;

    public Person(final PersonKey key, final String lastName, final double salary) {
        this.key = key;
        this.lastName = lastName;
        this.salary = salary;
    }
}