package ca.ets.da.rest;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import ca.ets.da.rest.model.Change;
import ca.ets.da.rest.model.Revision;
import ca.ets.da.rest.services.ChangeService;
import ca.ets.da.rest.services.FileService;
import ca.ets.da.rest.services.RevisionService;
import ca.ets.da.rest.util.FileCodecBase64;

@RestController
@EnableAutoConfiguration
public class GerritController {
	
	private static final int LIMIT_VALUE = 10;

	@Autowired
	private GerritApi gerritApi;
	
    private ChangeService changeService;
	
    private RevisionService revisionService;
	
    private FileService fileService;

    @Autowired
    public void setChangeService(ChangeService changeService) {
        this.changeService = changeService;
    }

    @Autowired
    public void setRevisionService(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
   
    @RequestMapping("/code-review-da-rest")
	String home() throws Exception {
    	
    	String fileName = null;
    	String changeId = null;
    	BinaryResult binaryContent = null;
    	FileOutputStream fio = null;
    	
    	Changes changes = gerritApi.changes();
    	
//    	List<ChangeInfo> changesList = gerritApi.changes().query().withLimit(LIMIT_VALUE).get();
    	
    	List<ChangeInfo> changesList = new ArrayList<ChangeInfo>();
    	
    	ChangeInfo elementChange = new ChangeInfo();
//    	elementChange.id = "toolchain%2Fjack~ub-jack~Id73b57b9a072d3dc3e8011b606f3c272d407ce2f";
    	Change changeDB = changeService.getChangeById(2);
//    	elementChange.id = "platform%2Feclipse.platform.ui~master~I5304fbf3034113b14fd8b8a8263ece3c6eaaf856";
    	elementChange.id = changeDB.getChangeId();
    	
    	changesList.add(elementChange);
    	
    	ChangeApi changeApi = null;
    	RevisionApi revisionApi = null;
    	
    	System.out.println("With Limit "+LIMIT_VALUE);
    	
    	Map<String, RevisionInfo> revisions = null;
    	
    	RevisionInfo revision = null;
    	
    	Map<String, FileInfo> files;
    	
    	FileInfo file = null;
    	
    	String resultStr = null;
    	Revision revisionDB = revisionService.getRevisionById(7);
    	
    	for(ChangeInfo change : changesList)
    	{
    		changeApi = changes.id(change.id);
    		
//    		revisionApi = changeApi.current();
    		
//    		revisionApi = changeApi.revision("8357718e1059df99e221a52c039af3016ca05323");
//    		revisionApi = changeApi.revision("966556a62c48f54a7b34127ca1ca52330028ed7b");
    		revisionApi = changeApi.revision(revisionDB.getRevId());
    		
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
                		
//                		resultStr = binaryContent.asString();
//                		
//                		if (binaryContent.isBase64())
//                		{
//                			resultStr = new String(DatatypeConverter.parseBase64Binary(resultStr));
//                		}
                		
                		fileName =  fileId.substring(fileId.lastIndexOf("/")+1);
                		
                    	fio = new FileOutputStream("C:\\Hilali\\Gerrit\\Encoded\\"+fileName);
                		
                    	binaryContent.writeTo(fio);
                    	fio.flush();
                    	fio.close();                		
                    	
                    	FileCodecBase64.decode("C:\\Hilali\\Gerrit\\Encoded\\"+fileName, "C:\\Hilali\\Gerrit\\"+fileName);
                    	
//                		fio =new FileOutputStream("C:\\Hilali\\Gerrit\\"+fileName);
//                		
//                		PrintWriter p = new PrintWriter(fio, true);
//                		p.print(resultStr);
//                    	fio.close();  
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
