package com.nostrasoft.da.rest.model;

import java.io.IOException;

import org.kohsuke.github.GitHub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfig {
	@Bean
	public GitHub githubRepository() throws IOException {
		return GitHub.connect();
	}
}
