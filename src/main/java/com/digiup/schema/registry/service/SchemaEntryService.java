package com.digiup.schema.registry.service;

import com.digiup.schema.registry.model.SchemaEntry;
import com.digiup.schema.registry.repository.SchemaEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchemaEntryService {

    public SchemaEntryService(@Autowired SchemaEntryRepository schemaEntryRepository) {
        this.schemaEntryRepository = schemaEntryRepository;
    }

    public List<SchemaEntry> findAll() {
        return schemaEntryRepository.findAll();
    }

    public Optional<SchemaEntry> findById(Long id) {
        return schemaEntryRepository.findById(id);
    }

    public Optional<SchemaEntry> findByName(String name) {
        return schemaEntryRepository.findByName(name);
    }

    public Optional<SchemaEntry> save(Optional<SchemaEntry> schemaOptional) {
        schemaOptional.ifPresent(schemaEntry -> schemaEntryRepository.save(schemaEntry));
        return schemaOptional;
    }

    public Optional<SchemaEntry> delete(Optional<SchemaEntry> schemaOptional) {
        schemaOptional.ifPresent(schemaEntry -> schemaEntryRepository.delete(schemaEntry));
        return schemaOptional;
    }

    public Optional<SchemaEntry> delete(String name) {
        Optional<SchemaEntry> schemaOptional = schemaEntryRepository.findByName(name);
        schemaOptional.ifPresent(schemaEntry -> schemaEntryRepository.delete(schemaEntry));
        return schemaOptional;
    }

    private SchemaEntryRepository schemaEntryRepository;
}
