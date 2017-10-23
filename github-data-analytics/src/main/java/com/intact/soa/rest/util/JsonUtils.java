package com.intact.soa.rest.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils 
{
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	
	static
	{
		jsonMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	
	public static String toJSON(Object pObject) throws JsonGenerationException, JsonMappingException, IOException
	{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		
    	jsonMapper.writerWithDefaultPrettyPrinter().writeValue(bo, pObject);
    	
		return new String(bo.toByteArray());
	}
}
