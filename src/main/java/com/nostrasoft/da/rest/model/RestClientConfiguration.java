package com.nostrasoft.da.rest.model;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration 
public class RestClientConfiguration { 
 
 /**
  * Configures the Jackson {@link ObjectMapper} to ignore unknown properties on the client side. E.g. 
  * {@link LineItem#getTotal()} causes Jackson to consider {@code total} a property and fails to bind the object as 
  * there's no setter accepting a value. 
  *  
  * @return 
  */ 
 @Bean 
 public RestOperations restOperations() { 
 
//  Jaxb2RootElementHttpMessageConverter converter = new Jaxb2RootElementHttpMessageConverter(); 
//  converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML)); 
// 
//  List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>(); 
//  converters.add(converter); 
 
  RestTemplate template = new RestTemplate(); 
//  template.setMessageConverters(converters); 
 
  return template; 
 } 
}