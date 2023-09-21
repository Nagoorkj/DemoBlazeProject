package com.steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = { "src/test/resources/Final/Project/DemoBlaze.feature" }, glue = {
		"com.steps" }, monochrome = true, plugin = { "pretty", "html:target/output/results.html" })

public class TestRunner extends AbstractTestNGCucumberTests {

}