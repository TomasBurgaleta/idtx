package com.idtx.price;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/idtx/price/cucumber")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.idtx.price")
public class CucumberTest {
}
