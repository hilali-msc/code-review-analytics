package com.nostrasoft.da.rest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class GerritController {


   

    @RequestMapping("/github-da-rest")
	String home() {
		return "<B>GitHub Data Analytics Rest Tools, the way you get it !</B>";
	}
}
