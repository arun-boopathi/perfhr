package com.perficient.hr.controller;

import org.springframework.beans.factory.annotation.Value;

public class AbstractController {
	
	@Value("${ptoFilesOutputDir}")
	public String ptoFilesOutputDir;
}
