package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTests {

	Faker faker;
	Store storePayload;
	public Logger logger;  // for logs

	/**
	 * Sets up test data before execution.
	 * Initializes Faker instance and populates the store payload with random data.
	 */
	@BeforeTest
	public void setupData() {
		faker = new Faker();
		storePayload = new Store();

		storePayload.setId(faker.number().numberBetween(1, 10));
		storePayload.setPetId(faker.number().numberBetween(1, 10));
		storePayload.setQuantity(faker.number().numberBetween(1, 100));
		storePayload.setShipDate(DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
		storePayload.setStatus("placed");
		storePayload.setComplete(true);	

		// logs
		logger = LogManager.getLogger(this.getClass());
	}

	/**
	 * Tests the placement of a new order.
	 * Verifies that the response status code is 200 and the order ID matches the payload.
	 */
	@Test(priority=1)
	public void testPlaceOrder() {

		logger.info("********** Placing an order for a pet ************");

		Response response = StoreEndPoints.placeOrder(storePayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id"), storePayload.getId());

		logger.info("********** Order an placed for a pet ************");				
	}

	/**
	 * Tests retrieval of an order by its ID.
	 * Ensures the response contains the expected order details.
	 */
	@Test(priority=2)
	public void testFindOrder() {

		logger.info("********** Finding purchase order by ID ************");

		Response response = StoreEndPoints.findOrder(this.storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id"), storePayload.getId());

		logger.info("********** Found purchase order by ID ************");
	}

	/**
	 * Tests deletion of a purchase order.
	 * Verifies successful deletion and correct confirmation message.
	 */
	@Test(priority=3)
	public void tesDeleteOrder() {

		logger.info("********** Deleting purchase order by ID ************");

		Response response = StoreEndPoints.deleteOrder(this.storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		Assert.assertEquals(response.jsonPath().getInt("message"), storePayload.getId());

		logger.info("********** Deleted purchase order by ID ************");
	}

}
