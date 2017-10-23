package com.intact.soa.api;

import java.util.Date;
import java.util.List;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommit.File;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

public class Main {

	public static void main(String[] args) throws Exception {
		
		GitHub hub = GitHub.connect();
		listFiles(hub);
		

	}
	
	private static void listFiles(GitHub gitHub) throws Exception {
        GHRepository repo = gitHub.getRepository("iluwatar/java-design-patterns");
        PagedIterable<GHCommit> commits = repo.queryCommits().since(new Date(2016,10,21)).list();
        
        String fileName = null;
        StringBuilder buffer = new StringBuilder("");
        
        
        for (GHCommit commit : commits) {
        	
        	if (null != commit.getAuthor())
        	{
                
            	List<File> files = commit.getFiles();
            	
            	for(File file:files)
            	{
            		fileName = file.getFileName();
            		
            		if (fileName.contains(".java"))
            		{
            			buffer.append(fileName);
            			buffer.append(" ");
            		}
            	}
            	
            	
            	if (!buffer.toString().isEmpty())
            	{            	
                	System.out.println("Author Name   : "+commit.getAuthor().getName());
                	System.out.println("Author Email  : "+commit.getAuthor().getEmail());
                	System.out.println("Files Changed : "+buffer.toString().trim());
            	}
            	
        	}
            
        }
    }	

}
