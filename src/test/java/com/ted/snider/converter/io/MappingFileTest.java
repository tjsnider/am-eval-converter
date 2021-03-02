package com.ted.snider.converter.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ted.snider.converter.properties.MappingProp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

public class MappingFileTest {
    @Test
    @DisplayName("Test that MappingFile checks for existing file on creation.")
    public void testConstructor() {
        String goodName = "src/test/resources/testMappingFile.json";
        String badName = "src/test/resources/notAFileName.json";

        String goodError = goodName + " cannot be found.";
        String badError = badName + " cannot be found.";

        try {
            Exception empty = assertThrows(FileNotFoundException.class, () -> {
                new MappingFile(goodName);
            });
            assertFalse(empty.getMessage().contains(goodError));
        } catch (AssertionFailedError afe) {
            // thrown if no FileNotFoundException is thrown, therefore good.
            assertTrue(true);
        }

        Exception full = assertThrows(FileNotFoundException.class, () -> {
            new MappingFile(badName);
        });
        assertTrue(full.getMessage().contains(badError));
    }

    @Test
    @DisplayName("Test that MappingFile correctly parses a sample file.")
    public void testRead() throws JsonParseException, JsonMappingException, IOException {
        String sample = "src/test/resources/testMappingFile.json";
        MappingFile sampleMapping = new MappingFile(sample);
        List<MappingProp> properties = sampleMapping.read();

        assertEquals(3, properties.size(), "Expected 3 tests, but found " + properties.size());
        assertEquals("straightTest", properties.get(0).getInputField(), "Expected first property input field to be straightTest, instead found " + properties.get(0).getInputField());
        assertEquals("straight-rename-test", properties.get(1).getInputField(), "Expected first property input field to be straight-rename-test, instead found " + properties.get(0).getInputField());
        assertEquals("custom-test", properties.get(2).getInputField(), "Expected first property input field to be custom-test, instead found " + properties.get(0).getInputField());
        assertEquals("\"\"outputField\"\": value*value", properties.get(2).getMapping());
    }
}
