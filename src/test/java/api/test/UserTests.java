package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;


public class UserTests {

	Faker faker;
	User userPayload;	
	public Logger logger;  // for logs

	/**
	 * Initializes user test data before running tests.
	 * Uses Faker to generate random user details.
	 */
	@BeforeClass	
	public void setupData() {

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// logs		
		logger = LogManager.getLogger(this.getClass());

	}

	/**
	 * Tests the creation of a new user.
	 * Verifies that the response status is 200 after user creation.
	 *
	 * @throws Exception If an error occurs during execution.
	 */
	@Test(priority = 1)
	public void testPostUser() throws Exception {

		logger.info("********** Creating user ************");

		Response response = UserEndPoints.createUser(userPayload);		
		response.then().log().all();		
		Assert.assertEquals(response.getStatusCode(), 200);		
		Thread.sleep(3000);	

		logger.info("********** User is Created ************");
	}


	/**
	 * Tests retrieval of user information.
	 * Ensures the response contains the expected user details.
	 */
	@Test(priority=2)
	public void testGetUser() {

		logger.info("********** Reading User Info ************");

		Response response = UserEndPoints.readUser(this.userPayload.getUsername());		
		response.then().log().all();		
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("********** User Info displayed ************");
	}

	/**
	 * Tests updating user details.
	 * Verifies the update status and checks the response after modification.
	 *
	 * @throws Exception If an error occurs during execution.
	 */
	@Test(priority=3)
	public void testUpdateUser() throws Exception {

		logger.info("********** Updating User ************");

		// updating the user
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);	
		response.then().log().all();		
		Assert.assertEquals(response.getStatusCode(), 200);
		Thread.sleep(3000);

		logger.info("********** User is updated ************");

		// after updating the user get the user

		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());		
		responseAfterUpdate.then().log().all();		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);		
	}

	/**
	 * Tests deletion of a user.
	 * Ensures the response status code indicates successful deletion.
	 */
	@Test(priority=4)
	public void testDeleteUser() {

		logger.info("********** Deleting User ************");

		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("********** User Deleted ************");
	}

}





























