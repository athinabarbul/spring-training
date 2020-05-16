package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.dtos.OrdersDto;
import ro.msg.learning.shop.exceptions.StrategyException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CreateNewOrderIntegrationTest {

	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	OrdersDto ordersDto;
	OrderDetailDto orderDetailDto;

	@Test
	public void createOrderSuccessTest() {
		String s = "{\n" +
				"    \"createdAt\": \"2020-01-01T00:00:00\",\n" +
				"    \"addressCountry\": \"Romania\",\n" +
				"    \"addressCounty\": \"Timis\",\n" +
				"    \"addressCity\": \"Timisoara\",\n" +
				"    \"addressStreet\": \"Teiului 6\",\n" +
				"    \"orderDetailDtos\": [\n" +
				"        {\n" +
				"            \"quantity\": 2,\n" +
				"            \"productId\": 1\n" +
				"        }\n" +
				"    ]\n" +
				"}";
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(s, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				(String) createURLWithPort("/orders/new-order"), HttpMethod.POST, entity, String.class);
		String actual = response.getStatusCode().toString();
		assertEquals("200 OK", actual);
	}

	@Test
	public void createOrderFailedTest() {
		String s = "{\n" +
				"    \"createdAt\": \"2020-01-01T00:00:00\",\n" +
				"    \"addressCountry\": \"Romania\",\n" +
				"    \"addressCounty\": \"Timis\",\n" +
				"    \"addressCity\": \"Timisoara\",\n" +
				"    \"addressStreet\": \"Teiului 6\",\n" +
				"    \"orderDetailDtos\": [\n" +
				"        {\n" +
				"            \"quantity\": 120,\n" +
				"            \"productId\": 1\n" +
				"        }\n" +
				"    ]\n" +
				"}";
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(s, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				(String) createURLWithPort("/orders/new-order"), HttpMethod.POST, entity, String.class);
		String actual = response.getStatusCode().toString();
		assertEquals("500 INTERNAL_SERVER_ERROR", actual);
	}


	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
