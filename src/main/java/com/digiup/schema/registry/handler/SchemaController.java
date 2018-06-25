package com.digiup.schema.registry.handler;

import com.digiup.schema.registry.model.Schema;
import com.digiup.schema.registry.service.SchemaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/schema")
public class SchemaController {
    public SchemaController(@Autowired SchemaService schemaService) {
        this.schemaService = schemaService;
    }

    @GetMapping
    public List<String> list() {
        return schemaService.findAll().stream().map(Schema::getName).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    @ResponseBody
    public Schema get(@PathVariable("name") String name) {
        return schemaService.findByName(name).orElse(null);
    }

    @PostMapping("/{name}")
    @ResponseBody
    public Schema create(@PathVariable("name") String name, @RequestBody Schema schema) {
        return schemaService.save(Optional.of(schema)).orElse(null);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public Optional<Schema> update(@PathVariable("id") Long id, @RequestBody Schema schema) {
        return schemaService.save(Optional.of(schema));
    }


    @GetMapping("/{name}.avsc")
    @ResponseBody
    public String getAvro(@PathVariable("name") String name) {
        Optional<Schema> schemaOptional = schemaService.findByName(name);
        final StringBuffer buffer = new StringBuffer();
        schemaOptional.ifPresent(s -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                buffer.append(mapper.writeValueAsString(s));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return buffer.toString();
    }

    @PostMapping("/{name}/associate")
    @ResponseBody
    public Schema associate(@PathVariable("name") String name, @RequestBody Long schemaEntryId) {
        final Optional<Schema> schemaOptional = schemaService.findByName(name);
        schemaOptional.ifPresent(s -> schemaService.associate(schemaOptional, schemaEntryId));
        return schemaOptional.orElse(null);
    }

    private SchemaService schemaService;
}
