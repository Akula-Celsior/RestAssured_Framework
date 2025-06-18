package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	/**
	 * Tests the creation of a new user using data from a DataProvider.
	 * Verifies that the response status code is 200.
	 *
	 * @param userID The unique user ID.
	 * @param userName The username.
	 * @param firstName The user's first name.
	 * @param lastName The user's last name.
	 * @param email The user's email address.
	 * @param password The user's password.
	 * @param phone The user's phone number.
	 */
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String firstName, String lastName, String email, String password, String phone) 
	{
		User userPayload = new User();

		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);

		Response response = UserEndPoints.createUser(userPayload);				
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	/**
	 * Tests the deletion of a user by their username using a DataProvider.
	 * Verifies that the response status code is 200.
	 *
	 * @param userNam The username of the user to be deleted.
	 */
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByUserName(String userNam)
	{		
		Response response = UserEndPoints.deleteUser(userNam);
		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
