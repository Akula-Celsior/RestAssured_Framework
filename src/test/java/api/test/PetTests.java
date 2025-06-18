package api.test;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tags;
import io.restassured.response.Response;

public class PetTests {

	Faker faker;
	Category category;
	Tags tags;
	Pet petPayload;
	public Logger logger;  // for logs

	/**
	 * Sets up test data before executing the test suite.
	 * Uses Faker to generate random pet details for API testing.
	 */
	@BeforeTest
	public void setupData() {
		faker = new Faker();
		category = new Category();
		tags = new Tags();
		petPayload = new Pet();

		// category
		category.setId(faker.number().numberBetween(1, 100));
		category.setName(faker.animal().name());

		//tags
		tags.setId(faker.number().numberBetween(1, 100));
		tags.setName("cute");

		// petpayload		 
		petPayload.setId(faker.number().numberBetween(1, 100));
		petPayload.setCategory(category);		 
		petPayload.setName(faker.animal().name());
		petPayload.setPhotoUrls(Arrays.asList("http://example.com/photo.jpg"));
		petPayload.setTags(Arrays.asList(tags));
		petPayload.setStatus("available");

		// logs
		logger = LogManager.getLogger(this.getClass());
	}

	/**
	 * Tests adding a new pet to the store.
	 * Verifies that the response status code is 200 and that the pet ID matches the request payload.
	 */
	@Test(priority=1)
	public void testAddPet() {

		logger.info("********** Adding a new pet to the store ************");

		Response response = PetEndPoints.addPet(petPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id"), petPayload.getId());

		logger.info("********** Added a new pet to the store ************");

	}

	/**
	 * Tests retrieving a pet from the store by its ID.
	 * Ensures the pet details match the request payload.
	 */
	@Test(priority=2)
	public void testFindPet() {

		logger.info("********** Finding pet by ID ************");

		Response response = PetEndPoints.findPet(this.petPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id"), petPayload.getId());

		logger.info("********** Found pet by ID ************");
	}

	/**
	 * Tests updating a pet's name and status using form data.
	 * Verifies the response status and ensures the updated data matches expectations.
	 */
	@Test(priority=3)
	public void testUpdatePet() {

		logger.info("********** Updates a pet in the store with form data ************");

		Response response = PetEndPoints.updatePet(this.petPayload.getId(), "goat", "sold");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("message"), petPayload.getId());

		logger.info("********** Updated a pet in the store with form data ************");

		Response updatedResponse = PetEndPoints.findPet(this.petPayload.getId());
		updatedResponse.then().log().all();
		Assert.assertEquals(updatedResponse.jsonPath().getString("name"), "goat");
		Assert.assertEquals(updatedResponse.jsonPath().getString("status"), "sold");
	}

	/**
	 * Tests deleting a pet from the store by its ID.
	 * Ensures the response status code indicates successful deletion.
	 */
	@Test(priority=4)
	public void testDeletePet() {

		logger.info("********** Deleting a pet by ID ************");

		Response response = PetEndPoints.deletePet(this.petPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("message"), petPayload.getId());

		logger.info("********** Deleted a pet by ID ************");
	}

}
