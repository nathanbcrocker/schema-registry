package com.digiup.schema.registry.repository;

import com.digiup.schema.registry.model.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchemaRepository extends JpaRepository<Schema, Long> {
    Optional<Schema> findByName(String name);
}
