package com.intact.soa.rest.util;

import java.io.ByteArrayOutputStream;

import com.fasterxml.jackson.databind.DeserializationFeature;
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
	
	
	public static String toJSON(Object pObject) 
	{
		String jsonString = null;
		
		try
		{
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			
	    	jsonMapper.writerWithDefaultPrettyPrinter().writeValue(bo, pObject);
	    	
	    	jsonString = new String(bo.toByteArray());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
    	
		return jsonString;
	}
}
