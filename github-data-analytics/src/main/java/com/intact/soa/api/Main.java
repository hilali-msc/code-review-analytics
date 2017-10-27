package com.intact.soa.api;

import java.time.Duration;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommit.File;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

import com.intact.soa.rest.model.Commit;
import com.intact.soa.rest.model.GithubMapper;

public class Main {

	public static void main(String[] args) throws Exception {

		GitHub hub = GitHub.connect();
		listFiles(hub);

	}

	private static void listFiles(GitHub gitHub) throws Exception {
		GHRepository repo = gitHub.getRepository("spring-projects/spring-boot");
		
		
		Date thisYearMinus4 = new Date((Calendar.getInstance().getTime()).toInstant().minus(Duration.of(4*365L, ChronoUnit.valueOf("DAYS")) ).toEpochMilli());
		PagedIterable<GHCommit> commits = repo.queryCommits().since(thisYearMinus4).list();
		
		System.out.println("Nb commits : "+commits.asSet().size());

		String fileName = null;
		StringBuilder buffer = new StringBuilder("");

		GHUser userCommiter = null;
		List<File> filesByCommit = null;
		Commit currentCommit = null;
		
		List<Commit> rawCommits = new ArrayList<Commit>();

		int count = 0;

		PagedIterable<GHCommitComment> iterableComments = null;
		for (GHCommit commit : commits) {
				currentCommit = GithubMapper.buildCommit(commit);				
				rawCommits.add(currentCommit);

			if (count++ > 5) {
				break;
			}

		}
		
		for(Commit rawCommit:rawCommits)
		{
			System.out.println(rawCommit);
		}
	}

}
