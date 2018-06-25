package com.digiup.schema.registry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Schema {
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
        return "record";
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<SchemaEntry> getFields() {
        return schemaEntries;
    }

    public void setFields(List<SchemaEntry> schemaEntries) {
        this.schemaEntries = schemaEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schema schema = (Schema) o;
        return Objects.equals(name, schema.name) &&
                Objects.equals(schemaEntries, schema.schemaEntries);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, schemaEntries);
    }

    @Id
    @GeneratedValue
    @Column(name = "schema_id")
    private Long id = new Long(0);

    @Column(name = "schema_name")
    private String name;

    private String namespace;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "schema_schema_entry",
            joinColumns = @JoinColumn(name = "schema_id"),
            inverseJoinColumns = @JoinColumn(name = "schema_entry_id")
    )
    private List<SchemaEntry> schemaEntries = new ArrayList<>();
}
