package com.intact.soa.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHCommit.File;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

import com.intact.soa.rest.model.Commit;

public class Main {

	public static void main(String[] args) throws Exception {

		GitHub hub = GitHub.connect();
		listFiles(hub);

	}

	private static void listFiles(GitHub gitHub) throws Exception {
		GHRepository repo = gitHub.getRepository("iluwatar/java-design-patterns");
		PagedIterable<GHCommit> commits = repo.queryCommits().since(new Date(2016, 10, 21)).list();

		String fileName = null;
		StringBuilder buffer = new StringBuilder("");

		GHUser userCommiter = null;
		List<File> filesByCommit = null;
		Commit currentCommit = null;
		
		List<Commit> rawCommits = new ArrayList<Commit>();

		int count = 0;

		PagedIterable<GHCommitComment> iterableComments = null;
		for (GHCommit commit : commits) {

			userCommiter = commit.getCommitter();

			filesByCommit = commit.getFiles();

			for (File file : filesByCommit) {
				fileName = file.getFileName();

				if (fileName.contains(".java")) {
					buffer.append(fileName);
					buffer.append(" ");
				}
			}

			if (!buffer.toString().isEmpty()) {
				
				currentCommit = new Commit();
				
				currentCommit.setId(commit.getSHA1());
				iterableComments = commit.listComments();
				
				StringBuilder bufferComments = new StringBuilder("");
				
				for(GHCommitComment comment:iterableComments)
				{
					bufferComments.append(comment.getBody());
					bufferComments.append(" ");
				}
				
				currentCommit.setMessage(bufferComments.toString());
				count++;
				if (null != userCommiter) {
					
					currentCommit.setCommiterName(userCommiter.getName());
					currentCommit.setCommiterEmail(userCommiter.getEmail());

				}
				currentCommit.setFiles(buffer.toString().trim());
				
				currentCommit.setDate(commit.getCommitDate().toString());
//				currentCommit.setType(commit.getLastStatus().getDescription());
				
				rawCommits.add(currentCommit);
			}

			if (count > 30) {
				break;
			}

		}
		
		for(Commit rawCommit:rawCommits)
		{
			System.out.println(rawCommit);
		}
	}

}
