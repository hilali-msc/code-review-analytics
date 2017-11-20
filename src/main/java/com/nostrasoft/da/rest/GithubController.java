package com.nostrasoft.da.rest;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nostrasoft.da.rest.model.Commit;
import com.nostrasoft.da.rest.model.Commits;
import com.nostrasoft.da.rest.model.GithubMapper;

@RestController
@EnableAutoConfiguration
public class GithubController {

	private final GitHub hub;

    @Autowired
    public GithubController(GitHub hub) {
        this.hub = hub;
    }

    @RequestMapping("/github-da-rest")
	String home() {
		return "<B>GitHub Data Analytics Rest Tools, the way you get it !</B>";
	}

    @RequestMapping(value = "/github-rest/analytics")
	public Commits services(
			@RequestParam String repository,
			@RequestParam String owner)
			throws IOException {

        GHRepository repo = hub.getRepository(repository + "/" + owner);

		if (null == repo)
		{
			return null;
		}

		Date thisYearMinus4 = new Date((Calendar.getInstance().getTime())
				.toInstant()
				.minus(Duration.of(4 * 365L, ChronoUnit.valueOf("DAYS")))
				.toEpochMilli());
		PagedIterable<GHCommit> rawCommits = repo.queryCommits()
				.since(thisYearMinus4).list();

		Commit currentCommit;

		Commits commits = new Commits();

		for (GHCommit commit : rawCommits) {
			currentCommit = GithubMapper.buildCommit(commit);

			if (null == currentCommit) {
				continue;
			}

			commits.add(currentCommit);
		}

		return commits;

	}

    @RequestMapping(value = "/github-rest/notify")
    public String notify(
            @RequestParam String repository,
            @RequestParam String owner)
            throws IOException {
        GHRepository repo = hub.getRepository(repository + "/" + owner);

        if (null == repo)
        {
            return "Not valid Github Repository!";
        }


        return owner + " is the owner of Github Repository " + repository;

    }
}
