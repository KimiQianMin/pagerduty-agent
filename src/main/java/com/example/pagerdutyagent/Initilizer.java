package com.example.pagerdutyagent;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.pagerdutyagent.service.UserService;

@Component
@Scope("singleton")
public class Initilizer {

	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private UserService userService;
	
	public void init() {
		
	}

}
