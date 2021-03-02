package com.ted.snider.converter.mapping;

import java.lang.reflect.InvocationTargetException;

import com.ted.snider.converter.properties.MappingProp;

import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.IExpressionEvaluator;

public class StraightMapping implements Mapping {
	private String field;
	private String type;
	private IExpressionEvaluator eval;

	public StraightMapping(MappingProp value) throws Exception {
		eval = CompilerFactoryFactory.getDefaultCompilerFactory().newExpressionEvaluator();
		eval.setExpressionType(String.class);
		switch (value.getMapping()) {
			case "integer":
				eval.setParameters(new String[] { "field", "value" }, new Class[] { String.class, Long.class });
				eval.cook("new StringBuilder().append(\"\\\"\").append(field).append(\"\\\": \").append(value).toString()");
				break;
			case "float":
				eval.setParameters(new String[] { "field", "value" }, new Class[] { String.class, Double.class });
				eval.cook("new StringBuilder().append(\"\\\"\").append(field).append(\"\\\": \").append(value).toString()");
				break;
			default:
				eval.setParameters(new String[] { "field", "value" }, new Class[] { String.class, String.class });
				eval.cook("new StringBuilder().append(\"\\\"\").append(field).append(\"\\\": \\\"\").append(value).append(\"\\\"\").toString()");
		}

		field = value.getInputField();
		type = value.getMapping();
	}

	@Override
	public String evaluate(String[] arguments) throws InvocationTargetException {
		String res;

		switch(type) {
			case "integer" : res = (String) eval.evaluate(new Object[] { field, Long.parseLong(arguments[0]) });
							 break;
			case "float" : res = (String) eval.evaluate(new Object[] { field, Double.parseDouble(arguments[0]) });
						   break;
			default : res = (String) eval.evaluate(new Object[] { field, arguments[0] });
		}
		return res;
	}
}
