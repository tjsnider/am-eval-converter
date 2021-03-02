package com.ted.snider;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.ted.snider.converter.io.MappingFile;
import com.ted.snider.converter.io.XMLFile;
import com.ted.snider.converter.properties.MappingProp;

/**
 * Driver for xml to json mappable conversion
 */
public final class App {
    private App() {
    }

    /**
     * Read input file and mapping file; delegate to appropriate parsers; evaluate;
     * and print results
     * 
     * @param args input file name, mapping file name, and output file name in that
     *             order
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String infile = args[0];
        String mappingfile = args[1];
        String outfile = args[2];

        List<MappingProp> properties = new MappingFile(mappingfile).read();
        XMLFile source = new XMLFile(infile, properties);
        String output = source.applyMapping();

        File outFile = new File(outfile);
        if (!outFile.createNewFile()) {
            outFile.delete();
            outFile.createNewFile();
        }
        FileWriter writer = new FileWriter(outFile);
        writer.write(output);
        writer.close();

    }
}
