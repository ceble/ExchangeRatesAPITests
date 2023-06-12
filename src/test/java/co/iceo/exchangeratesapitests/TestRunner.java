package co.iceo.exchangeratesapitests;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/ExchangeRates.feature", glue = "co.iceo.exchangeratesapitests.steps")
@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class TestRunner {
}
