package com.nostrasoft.da;

import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

import com.nostrasoft.da.rest.model.Commit;
import com.nostrasoft.da.rest.model.GithubMapper;

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

		Commit currentCommit = null;
		
		List<Commit> rawCommits = new ArrayList<Commit>();

		File outputFile = new File("C:\\dev\\report\\spring-boot-commits.json");

		for (GHCommit commit : commits) {
				currentCommit = GithubMapper.buildCommit(commit);
				
				if (null == currentCommit)
				{
					continue;
				}
				
				FileUtils.writeStringToFile(outputFile,currentCommit.toString());
				FileUtils.writeStringToFile(outputFile,"\n");
				rawCommits.add(currentCommit);

		}
		
//		for(Commit rawCommit:rawCommits)
//		{
//			System.out.println(rawCommit);
//		}
	}

}
