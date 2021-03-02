package com.ted.snider.converter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ted.snider.converter.mapping.CustomMapping;
import com.ted.snider.converter.mapping.Mapping;
import com.ted.snider.converter.mapping.StraightMapping;
import com.ted.snider.converter.mapping.StraightRenameMapping;
import com.ted.snider.converter.properties.MappingProp;

//TODO: rename to something more representative.
/**
 * Class to read up mapping file and create the Map of Mapper objects indexed by
 * input field name.
 */
public class MapperConfiguration {
    /**
     * Probably don't need this method, but...
     */
    public MapperConfiguration() {
        // ???
    }

    public Map<String, Mapper> configure(List<MappingProp> properties) throws Exception {
        Map<String, MappingProp> intermediate = properties.stream()
                .collect(Collectors.toMap(prop -> prop.getInputField(), prop -> {
                    return (MappingProp) prop;
                }));
        Map<String, Mapper> mapper = null;
        try { 
            mapper = intermediate.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> {
                    Mapping eval = null;

                    try { 
                        switch (entry.getValue().getMapType()) {
                            case "straight":
                                eval = new StraightMapping(entry.getValue());
                            case "straight-rename":
                                eval = new StraightRenameMapping(entry.getValue());
                            default:
                                eval = new CustomMapping(entry.getValue());
                        }
                    } catch (Exception e) {
                        // To please the linter...
                        throw new IllegalStateException(e);
                    }
                            
                    return new Mapper(eval);
                }));
        } catch (Exception e) {
            // To please the linter...
            throw e;
        }
        return mapper;
    }
}
