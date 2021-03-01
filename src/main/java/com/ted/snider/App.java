package com.ted.snider;

import java.io.File;

import com.ted.snider.converter.io.MappingFile;
import com.ted.snider.converter.io.XMLFile;
import com.ted.snider.converter.properties.MappingProps;

/**
 * Driver for xml to json mappable conversion
 */
public final class App {
    private App() {
    }

    /**
     * Read input file and mapping file; delegate to appropriate parsers; evaluate; and print results
     * @param args input file name, mapping file name, and output file name in that order
     */
    public static void main(String[] args) {
        String infile = args[0];
        String mappingfile = args[1];
        String outfile = args[2]

        MappingProps properties = new MappingFile(mappingfile).read();
        XMLFile source = new XMLFile(infile, properties);
        String output = source.applyMapping();

        File outFile = new File(outfile);
    }
}
