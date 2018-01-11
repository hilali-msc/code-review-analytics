package ca.ets.da.rest.model;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gerrit.extensions.api.GerritApi;
import com.urswolfer.gerrit.client.rest.GerritAuthData;
import com.urswolfer.gerrit.client.rest.GerritRestApiFactory;

@Configuration
public class GerritConfig {
	@Bean
	public GerritApi gerritRestAPI() throws IOException {
		
		GerritRestApiFactory gerritRestApiFactory = new GerritRestApiFactory();
		
		GerritAuthData.Basic authData = new GerritAuthData.Basic("https://android-review.googlesource.com", "${gerrit.user}", "${gerrit.pwd}") ;
		
			
		return gerritRestApiFactory.create(authData);
	}
	
	@Bean
	public GerritMapper getGerritMapper()
	{
		return new GerritMapper();
	}
}
