package com.digiup.schema.registry.handler;

import com.digiup.schema.registry.model.Schema;
import com.digiup.schema.registry.model.SchemaEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchemaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void create() {
        this.restTemplate.getRestTemplate().getMessageConverters().add(new StringHttpMessageConverter());

        final String name = "test-user-schema-" + String.valueOf(new Random().nextLong());
        final String url = "http://localhost:" + port + "/api/v1/schema/" + name;
        Schema schema = new Schema();
        schema.setName(name);
        schema.setNamespace("com.digiup.user");
        schema.getFields().add(
                SchemaEntry.schemaEntry("firstName", "string","The given name for the user"));
        schema.getFields().add(
                SchemaEntry.schemaEntry("lastName", "string","The surname name for the user"));

        this.restTemplate.postForEntity(url, schema, Schema.class);

        ResponseEntity<Schema> response = this.restTemplate.getForEntity(url, Schema.class);
        assertEquals(schema.getName(), response.getBody().getName());
    }
}