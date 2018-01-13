package ca.ets.da.rest;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gerrit.extensions.api.GerritApi;
import com.google.gerrit.extensions.api.changes.ChangeApi;
import com.google.gerrit.extensions.api.changes.Changes;
import com.google.gerrit.extensions.api.changes.RevisionApi;
import com.google.gerrit.extensions.common.ChangeInfo;
import com.google.gerrit.extensions.common.FileInfo;
import com.google.gerrit.extensions.common.RevisionInfo;
import com.google.gerrit.extensions.restapi.BinaryResult;

@RestController
@EnableAutoConfiguration
public class GerritController {
	
	private static final int LIMIT_VALUE = 10;

	@Autowired
	private GerritApi gerritApi;
   
    @RequestMapping("/code-review-da-rest")
	String home() throws Exception {
    	
    	String fileName = null;
    	String changeId = null;
    	BinaryResult binaryContent = null;
    	FileOutputStream fio = null;
    	
    	Changes changes = gerritApi.changes();
    	
    	List<ChangeInfo> changesList = gerritApi.changes().query().withLimit(LIMIT_VALUE).get();
    	
    	ChangeApi changeApi = null;
    	RevisionApi revisionApi = null;
    	
    	System.out.println("With Limit "+LIMIT_VALUE);
    	
    	Map<String, RevisionInfo> revisions = null;
    	
    	RevisionInfo revision = null;
    	
    	Map<String, FileInfo> files;
    	
    	FileInfo file = null;
    	
    	String resultStr = null;
    	
    	for(ChangeInfo change : changesList)
    	{
    		changeApi = changes.id(change.id);
    		
    		revisionApi = changeApi.current();
    		
    		if (null == revisionApi)
    		{
    			continue;
    		}
    		
    		files = revisionApi.files();
    		
    		if (null == files)
    		{
    			continue;
    		}
    		
    		for(String fileId:files.keySet())
    		{
    			file = files.get(fileId);
    			
//    			System.out.println(fileId);
    			
    			try
    			{
                	binaryContent = revisionApi.file(fileId).content();
                
                	if ("text/x-gerrit-commit-message".equals(binaryContent.getContentType()))
                	{
                		continue;
                	}
                	
                	fileName =  fileId.substring(fileId.lastIndexOf("/")+1);
                	
                	if ("text/x-java".equals(binaryContent.getContentType()))
                	{
                		System.out.println(fileName);
                		
                		resultStr = binaryContent.asString();
                		
                		if (binaryContent.isBase64())
                		{
                			resultStr = new String(DatatypeConverter.parseBase64Binary(resultStr));
                		}
                		
                		fileName =  fileId.substring(fileId.lastIndexOf("/")+1);
                		
                		fio =new FileOutputStream("C:\\Hilali\\Gerrit\\"+fileName);
                		
                		PrintWriter p = new PrintWriter(fio, true);
                		p.print(resultStr);
                    	fio.close();  
                	}                	
                	 
                	
                	
//                	fio = new FileOutputStream("C:\\Hilali\\Gerrit\\"+fileName);
                	
//                	binaryContent.writeTo(fio);
//                	fio.flush();
//                	fio.close();     				
    			}
    			catch(Exception e)
    			{
    				System.out.println(e.getMessage());
    			}
    			
           			
    		}
    	}
    	
		return "<B>Code Review Gerrit based Data Analytics Rest Tools, the way you got it !</B>";
	}
    
}
