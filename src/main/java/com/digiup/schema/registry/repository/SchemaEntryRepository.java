package com.digiup.schema.registry.repository;

import com.digiup.schema.registry.model.SchemaEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchemaEntryRepository extends JpaRepository<SchemaEntry, Long> {
    Optional<SchemaEntry> findByName(String name);
}
