package com.nostrasoft.da.rest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class GerritController {


   

    @RequestMapping("/code-review-da-rest")
	String home() {
		return "<B>Code Review Gerrit based Data Analytics Rest Tools, the way you got it !</B>";
	}
}
