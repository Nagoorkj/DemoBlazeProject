package com.steps;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("Final/Project")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,html:target/output/results.html")

public class RunCucumberTest {
}
