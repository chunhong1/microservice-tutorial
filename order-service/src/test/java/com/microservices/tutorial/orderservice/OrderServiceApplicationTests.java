package com.microservices.tutorial.orderservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.hamcrest.Matchers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class OrderServiceApplicationTests {

	@Container
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup()
	{
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldSubmitOrder()
	{
		String requestBody = """
				{
				    "skuCode": "iphone_15",
				    "price": 5000,
				    "quantity": 1
				}
				""";

		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/order")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();

		assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));
	}

}
