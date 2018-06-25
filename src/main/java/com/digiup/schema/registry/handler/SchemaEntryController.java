package com.digiup.schema.registry.handler;

import com.digiup.schema.registry.model.Schema;
import com.digiup.schema.registry.model.SchemaEntry;
import com.digiup.schema.registry.service.SchemaEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/schemaEntries")
public class SchemaEntryController {
    public SchemaEntryController(@Autowired SchemaEntryService schemaEntryService) {
        this.schemaEntryService = schemaEntryService;
    }

    @GetMapping
    public List<String> list() {
        return schemaEntryService.findAll().stream().map(SchemaEntry::getName).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    @ResponseBody
    public Optional<SchemaEntry> get(@PathVariable("name") String name) {
        return schemaEntryService.findByName(name);
    }

    @PostMapping("/{name}")
    @ResponseBody
    public Optional<SchemaEntry> create(@PathVariable("name") String name, @RequestBody SchemaEntry schemaEntry) {
        schemaEntry.setName(name);
        Optional<SchemaEntry> schemaEntryOptional = Optional.of(schemaEntry);
        schemaEntryService.save(schemaEntryOptional);
        return schemaEntryOptional;
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public Optional<SchemaEntry> delete(@PathVariable("name") String name) {
        return schemaEntryService.delete(name);
    }

    @PatchMapping("/{name}")
    @ResponseBody
    public Optional<SchemaEntry> patch(@PathVariable("name") String name, @RequestBody String type) {
        Optional<SchemaEntry> schemaEntryOptional = schemaEntryService.findByName(name);
        schemaEntryOptional.ifPresent(s -> s.setType(type));
        return schemaEntryService.save(schemaEntryOptional);
    }

    private SchemaEntryService schemaEntryService;
}
