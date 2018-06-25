package com.digiup.schema.registry.service;

import com.digiup.schema.registry.model.Schema;
import com.digiup.schema.registry.model.SchemaEntry;
import com.digiup.schema.registry.repository.SchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class SchemaService {

    public SchemaService(@Autowired SchemaRepository schemaRepository,
                         @Autowired SchemaEntryService schemaEntryService) {
        this.schemaRepository = schemaRepository;
        this.schemaEntryService = schemaEntryService;
    }

    public List<Schema> findAll() {
        return schemaRepository.findAll();
    }

    public Optional<Schema> findById(Long id) {
        return schemaRepository.findById(id);
    }

    public Optional<Schema> findByName(String name) {
        return schemaRepository.findByName(name);
    }

    public Optional<Schema> save(Optional<Schema> schemaOptional) {
        schemaOptional.ifPresent(schema -> schemaRepository.save(schema));
        return schemaOptional;
    }

    public Optional<Schema> associate(Optional<Schema> schemaOptional, Long schemaEntryId) {
        return _associate(schemaOptional, () -> schemaEntryService.findById(schemaEntryId));
    }

    public Optional<Schema> associate(Optional<Schema> schemaOptional, Optional<SchemaEntry> schemaEntryOptional) {
        return _associate(schemaOptional, () -> schemaEntryOptional);
    }

    private Optional<Schema> _associate(Optional<Schema> schemaOptional, Supplier<Optional<SchemaEntry>> supplier) {
        schemaOptional.ifPresent(s -> supplier.get().ifPresent(e -> s.getFields().add(e)));
        return schemaOptional;
    }

    final private SchemaRepository schemaRepository;
    final private SchemaEntryService schemaEntryService;
}
