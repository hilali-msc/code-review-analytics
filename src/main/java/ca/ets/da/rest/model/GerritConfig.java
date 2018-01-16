package ca.ets.da.rest.model;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.google.gerrit.extensions.api.GerritApi;
import com.urswolfer.gerrit.client.rest.GerritAuthData;
import com.urswolfer.gerrit.client.rest.GerritRestApiFactory;

@Configuration
public class GerritConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public GerritApi gerritRestAPI() throws IOException {
		
		GerritRestApiFactory gerritRestApiFactory = new GerritRestApiFactory();
		
		String gerritProjectUrl = env.getProperty("eclipse.gerrit.projet.url");
		String gerritProjectUser = env.getProperty("eclipse.gerrit.user");
		String gerritProjectPwd = env.getProperty("eclipse.gerrit.pwd");
		
		GerritAuthData.Basic authData = new GerritAuthData.Basic("https://"+gerritProjectUrl, gerritProjectUser, gerritProjectPwd,true) ;
		return gerritRestApiFactory.create(authData);
	}
	
	@Bean
	public GerritMapper getGerritMapper()
	{
		return new GerritMapper();
	}
}
