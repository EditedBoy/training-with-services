package com.cassandra.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("test_entity")
public class TestEntity implements Serializable {

    @PrimaryKey
    private String key;

    @Column("name")
    private String name;

    @Column
    private boolean isAvailable;

    public TestEntity(final String key, final String name, final boolean isAvailable) {
        this.key = key;
        this.name = name;
        this.isAvailable = isAvailable;
    }
}
