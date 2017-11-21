package com.nostrasoft.da.rest.model;

import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommit.File;
import org.kohsuke.github.GHUser;

public class GithubMapper {
	
	GithubMapper() {}

	public static Commit buildCommit(GHCommit ghCommit) throws IOException {
		Commit commit = null;

		if (null == ghCommit) {
			return null;
		}

		String fileName = null;
		StringBuilder buffer = new StringBuilder("");

		GHUser userCommiter = null;
		List<File> filesByCommit = null;

		userCommiter = ghCommit.getCommitter();

		filesByCommit = ghCommit.getFiles();
		
		if ((null == filesByCommit) || (filesByCommit.isEmpty()) || (filesByCommit.size()>=100))
		{
			return null;
		}

		for (File file : filesByCommit) {
			fileName = file.getFileName();

			if (fileName.contains(".java")) {
				buffer.append(fileName);
				buffer.append(" ");
			}
		}

		if (!buffer.toString().isEmpty()) {

			commit = new Commit();

			commit.setId(ghCommit.getSHA1());

			commit.setMessage(ghCommit.getCommitShortInfo().getMessage());

			if (null != userCommiter) {

				commit.setCommiterName(userCommiter.getName());
				commit.setCommiterEmail(userCommiter.getEmail());

			}
			commit.setFiles(buffer.toString().trim());

			commit.setDate(ghCommit.getCommitDate().toString());
		}

		return commit;
	}

}
