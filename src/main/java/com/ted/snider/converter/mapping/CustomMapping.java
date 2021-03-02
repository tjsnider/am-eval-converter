package com.ted.snider.converter.mapping;

import java.lang.reflect.InvocationTargetException;

import com.ted.snider.converter.properties.MappingProp;

import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.IExpressionEvaluator;

public class CustomMapping implements Mapping {

	private IExpressionEvaluator eval;
	private String inputField;
	private String outputField;

	public CustomMapping(MappingProp value) throws Exception {
		this.eval = CompilerFactoryFactory.getDefaultCompilerFactory().newExpressionEvaluator();
		eval.setExpressionType(String.class);
		eval.setParameters(new String[] { "inputField", "outputField", "value" }, new Class[] { String.class, String.class, String.class });
		eval.cook(value.getMapping());

		this.inputField = value.getInputField();
		this.outputField = value.getOutputField();
	}

	@Override
	public String evaluate(String[] arguments) throws InvocationTargetException {
		return (String) eval.evaluate(new Object[] { this.inputField, this.outputField, arguments[0] });
	}
    
}
