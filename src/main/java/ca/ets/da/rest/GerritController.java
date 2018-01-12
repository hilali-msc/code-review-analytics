package ca.ets.da.rest;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gerrit.extensions.api.GerritApi;
import com.google.gerrit.extensions.api.changes.Changes;
import com.google.gerrit.extensions.common.ChangeInfo;
import com.google.gerrit.extensions.common.FileInfo;
import com.google.gerrit.extensions.common.RevisionInfo;
import com.google.gerrit.extensions.restapi.BinaryResult;

@RestController
@EnableAutoConfiguration
public class GerritController {

	@Autowired
	private GerritApi gerritApi;
   

    @RequestMapping("/code-review-da-rest")
	String home() throws Exception {
    	
    	Changes changes = gerritApi.changes();
    	
    	String fileName = null;
    	String changeId = null;
    	BinaryResult binaryContent = null;
    	FileOutputStream fio = null;
    	
    	List<ChangeInfo> changesList = changes.query("status:open").withLimit(10).get();
    	
    	Map<String, RevisionInfo> revisions = null;
    	
    	RevisionInfo revision = null;
    	
    	Map<String, FileInfo> files;
    	
    	FileInfo file = null;
    	
    	for(ChangeInfo change : changesList)
    	{
    		revisions = change.revisions;
    		
    		for(String revisionId:revisions.keySet())
    		{
    			revision = revisions.get(revisionId);
    			
    			files = revision.files;
    			
        		for(String fileId:files.keySet())
        		{
        			file = files.get(fileId);
        			
        			System.out.println(file.oldPath);
        			
//                	binaryContent = changes.id(change.id).revision(revision._number).file(file.oldPath).content();
//                	
//                	fio = new FileOutputStream("C:\\Hilali\\"+fileName);
//                	
//                	binaryContent.writeTo(fio);
//                	fio.flush();
//                	fio.close();            			
        		}
    		}
    	}
    	
		return "<B>Code Review Gerrit based Data Analytics Rest Tools, the way you got it !</B>";
	}
    
}
