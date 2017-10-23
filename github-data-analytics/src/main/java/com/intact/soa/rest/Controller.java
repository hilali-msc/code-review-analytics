package com.intact.soa.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intact.soa.rest.model.dp.service.BusinessServices;
import com.intact.soa.rest.util.JsonUtils;

@RestController
@EnableAutoConfiguration
public class Controller {
	
	@Autowired
	private RestOperations operations;
	
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	
	static
	{
		jsonMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}	
	
    @RequestMapping("/soa-rest")
    String home() {
        return "<B>SOA Rest Tools, the way you get it !</B>";
    } 

    
    @RequestMapping(value = "/soa-rest/services")
    public String services(@RequestParam(value = "format", required = false) String format,Model model) throws JsonGenerationException, JsonMappingException, IOException, JAXBException {
    	
    	BusinessServices services = operations.getForObject("https://dev-esb.iad.ca.inet/config/z/ws", BusinessServices.class);
    	
    	ByteArrayOutputStream bo = new ByteArrayOutputStream();
    	
    	String outContentFeed = null;
    	
    	if ("xml".equalsIgnoreCase(format))
    	{
    		JAXBContext jaxbContext = JAXBContext.newInstance(BusinessServices.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();    
    		
    		StringWriter sw = new StringWriter();
    		jaxbMarshaller.marshal(services, sw);
    		outContentFeed = sw.toString();    
    		
			StreamResult xmlOutput = new StreamResult(new StringWriter());
//			Transformer transformer = TransformerFactory.newInstance().newTransformer();
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			transformer.transform(services, xmlOutput);
			outContentFeed = xmlOutput.getWriter().toString();	
    		
    		
    		
    	}
    	else
    	{
    		outContentFeed = JsonUtils.toJSON(services);
    	}
    	
        model.addAttribute("jsonFeed", outContentFeed);    	

    	
        return outContentFeed;
    }
    
    @RequestMapping("/soa-rest/ws-status")
    String status() {
        return "<B>SOA Rest Tools, the way you get it !</B>";
    }
    
    @RequestMapping(value="/soa-rest/sonar", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void  dashSonar() 
    {
    	try{
    	    PrintWriter writer = new PrintWriter("DashBoard.txt", "UTF-8");
    	    writer.println("WebHook Called at ["+System.currentTimeMillis()+"]");
    	    writer.close();
    	    
    	    
    	} catch (IOException e) {
    	   // do something
    	}
    	
    }

}
