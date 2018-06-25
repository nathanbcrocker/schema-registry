package com.digiup.schema.registry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class SchemaEntry {
    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchemaEntry that = (SchemaEntry) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    public static SchemaEntry schemaEntry(String name, String type, String description) {
        SchemaEntry schemaEntry = new SchemaEntry();
        schemaEntry.setName(name);
        schemaEntry.setType(type);
        schemaEntry.setDescription(description);
        return schemaEntry;
    }

    @Id
    @GeneratedValue
    @Column(name = "schema_entry_id")
    private Long id = new Long(0);
    private String name;
    private String type;
    private String description;
}
