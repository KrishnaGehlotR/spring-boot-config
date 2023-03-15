package org.dev.microservicesdemo.controller;

import java.util.List;
import java.util.Map;

import org.dev.microservicesdemo.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GreetingController {

	@Value("${greeting.message:default value}")
	private String greetingMessage;

	@Value("${my.message}")
	private String myGreeting;

	@Value("Some static messages")
	private String staticMessage;

	@Value("my.list.values")
	private List<String> listValues;

	@Value("#{${db.connection}}")
	private Map<String, String> dbValues;

	@Autowired
	private Environment environment;

	@Autowired
	DatabaseConfig databaseConfig;

	@RequestMapping("/greeting")
	public String printGreeting() {
		return greetingMessage + "</br>" + myGreeting + "</br>" + staticMessage + "</br>" + listValues + "</br>"
				+ dbValues;

	}

	@RequestMapping("/dbsettings")
	public String printDatabseDetails() {
		return databaseConfig.getConnection() + "</br>" + databaseConfig.getHost() + "</br>" + databaseConfig.getPort();

	}

	@RequestMapping("/envdetails")
	public String evnDetails() {
		return environment.toString();
	}
}
