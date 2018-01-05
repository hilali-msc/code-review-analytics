package ca.ets.da.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gerrit.extensions.api.GerritApi;

@RestController
@EnableAutoConfiguration
public class GerritController {

	@Autowired
	private GerritApi gerritApi;
   

    @RequestMapping("/code-review-da-rest")
	String home() {
		return "<B>Code Review Gerrit based Data Analytics Rest Tools, the way you got it !</B>";
	}
}
