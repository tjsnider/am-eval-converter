package com.ted.snider.converter.properties;

import lombok.Data;

@Data
public class MappingProp {
    private String inputField;
    private String outputField;
    private String mapType; //TODO: change to an Enum of valid values
    private String mapping;
}