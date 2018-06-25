package com.digiup.schema.registry;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class TestOutput {
    @Test
    public void testUsersOutput() throws Exception {
        File file = new File("Users.avsc");
        Schema schema = new Schema.Parser().parse(file);
        GenericRecord user1 = new GenericData.Record(schema);
        user1.put("firstName", "Nathan");
        user1.put("lastName", "Crocker");
        assertNotNull(schema);
        assertEquals(2, schema.getFields().size());
        assertEquals("Crocker", user1.get("lastName"));
    }
}
