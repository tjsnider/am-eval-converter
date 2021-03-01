package com.ted.snider.converter;

import java.lang.reflect.InvocationTargetException;

import com.ted.snider.converter.mapping.Mapping;

/**
 * Class to wrap a Janino ExpressionEvaluator class.
 */
public class Mapper {
    private Mapping eval;

    /**
     * Instantiate an eval object from the specified ExpressionEvaluator.
     * 
     * @param eval
     */
    public Mapper(Mapping eval) {
        this.eval = eval;
    }

    /**
     * Map the specified value using the configured ExpressionEvaluator.
     * 
     * @param value
     * @return string representation of the json value entry; eg '"state":"MN"'
     * @throws InvocationTargetException 
     * @throws IllegalStateException
     */
    public String map(String value) throws InvocationTargetException, IllegalStateException {
        if (null == eval) { throw new IllegalStateException("Expression evaluator not instantiated."); }

        return eval.evaluate(new String[] { value });
    }
}
