package com.ted.snider.converter.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ted.snider.converter.properties.MappingProp;
import com.ted.snider.converter.properties.MappingProps;

public class MappingFile {
	private String file;

	public MappingFile(String mappingfile) throws FileNotFoundException {
		this.file = mappingfile;
		File input = new File(file);

		if (!input.exists()) {
			throw new FileNotFoundException(file + " cannot be found.");
		}
	}

	public List<MappingProp> read() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		MappingProp[] properties = mapper.readValue(new File(file), MappingProp[].class);

		return Arrays.asList(properties);
	}
    
}
