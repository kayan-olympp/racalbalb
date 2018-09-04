package com.racalbalb.demo.domain;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/site/cucumber"},
		glue = {"es.rubenjgarcia.cucumber.rest.steps"},
		features = "classpath:test.feature",
		//features = "classpath:features",
		strict = true)
public class DemoApplicationTests {

	private static WireMockRule wm;

	@BeforeClass
	public static void CucumberRestStepsTestCase() {
		wm = new WireMockRule(8080);
		wm.start();

		stubFor(
				get("/")
						.willReturn(ok())
		);

		stubFor(
				post("/withData")
						.withRequestBody(equalToJson("{\"test\": \"test\"}"))
						.willReturn(okJson("{\"foo\": \"bar\"}"))
		);

		stubFor(
				post("/withData")
						.withRequestBody(equalToJson("[{\"test\": \"test\"}]"))
						.willReturn(okJson("{\"foo\": \"bar\"}"))
		);

		stubFor(
				get(urlPathEqualTo("/withParams"))
						.withQueryParam("param", equalTo("paramValue"))
						.willReturn(okJson("[]"))
		);

		stubFor(
				get("/withHeaders")
						.withHeader("MyHeader", equalTo("MyHeaderValue"))
						.willReturn(
								aResponse()
										.withHeader("MyHeader", "MyHeaderValue")
										.withHeader("MultiHeader", "MultiHeaderValue1", "MultiHeaderValue2")
						)
		);

		stubFor(
				get("/withArray")
						.willReturn(okJson("[{\"foo\": \"bar\"}, {\"foo\": 3}, {\"foos\": [\"bar\", \"wee\"]}]"))
		);

		stubFor(
				get("/response")
						.willReturn(okJson("{\"foo\": \"bar\"}"))
		);

		stubFor(
				get("/response/bar")
						.willReturn(okJson("{\"bar\": \"wee\"}"))
		);

		stubFor(
				post("/withFile")
						.withMultipartRequestBody(
								aMultipart()
										.withName("file")
						)
						.willReturn(ok())
		);
	}
}
