package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {


	/**
	 * Reads and retrieves URLs from the properties file.
	 *
	 * @return ResourceBundle containing the URL mappings.
	 */
	static ResourceBundle getURl()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");  // "routes" is the name of the properties file

		return routes;
	}

	/**
	 * Creates a new user by sending a POST request.
	 *
	 * @param payload The user details to be created.
	 * @return Response containing the status and details of the created user.
	 */
	public static Response createUser(User payload){

		String post_url =getURl().getString("post_url");

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(post_url);

		return response;
	}

	/**
	 * Retrieves user details by sending a GET request.
	 *
	 * @param username The unique username of the user.
	 * @return Response containing the user details.
	 */
	public static Response readUser(String username){

		String get_url =getURl().getString("get_url");

		Response response = given()
				.pathParam("username", username)
				.when()
				.get(get_url);

		return response;
	}

	/**
	 * Updates an existing user's details using a PUT request.
	 *
	 * @param username The unique username of the user.
	 * @param payload The updated user details.
	 * @return Response indicating the update status.
	 */
	public static Response updateUser(String username, User payload){

		String put_url =getURl().getString("put_url");

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
				.when()
				.put(put_url);

		return response;
	}

	/**
	 * Deletes a user by sending a DELETE request.
	 *
	 * @param username The unique username of the user to be deleted.
	 * @return Response indicating the delete status.
	 */
	public static Response deleteUser(String username){

		String delete_url =getURl().getString("delete_url");

		Response response = given()
				.pathParam("username", username)
				.when()
				.delete(delete_url);

		return response;
	}

}
