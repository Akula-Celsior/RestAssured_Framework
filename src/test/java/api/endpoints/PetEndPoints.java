package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {

	/**
	 * Reads and retrieves URLs from the properties file.
	 *
	 * @return ResourceBundle containing the URL mappings.
	 */
	static ResourceBundle getUrl() {

		ResourceBundle routes = ResourceBundle.getBundle("routes"); // "routes" is the name of the properties file

		return routes;	
	}

	/**
	 * Add a new pet to the store by sending a POST request.
	 *
	 * @param payload The pet details to be added.
	 * @return Response containing the status and details of the newly added pet.
	 */
	public static Response addPet(Pet payload) {
		String addPetUrl = getUrl().getString("addPet");

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(addPetUrl);

		return response;
	}

	/**
	 * Find pet by ID using a GET request.
	 *
	 * @param id The unique ID of the pet.
	 * @return Response containing the pet's details.
	 */
	public static Response findPet(int id) {
		String getPetUrl = getUrl().getString("getPet");

		Response response = given()
				.header("api_key", "special-key")
				.accept(ContentType.JSON)
				.pathParam("petId", id)
				.when()
				.get(getPetUrl);

		return response;
	}

	/**
	 * Updates a pet's name and status in the store with form data using a POST request.
	 *
	 * @param id The unique ID of the pet.
	 * @param name The new name of the pet.
	 * @param status The updated status of the pet.
	 * @return Response indicating the update status.
	 */
	public static Response updatePet(int id, String name, String status){

		String updatePetUrl =getUrl().getString("updatePet");

		Response response = given()
				.contentType(ContentType.URLENC)
				.accept(ContentType.JSON)
				.pathParam("petId", id)
				.formParam("name", name)
				.formParam("status", status)
				.when()
				.post(updatePetUrl);

		return response;
	}

	/**
	 * Deletes a pet by its ID using a DELETE request.
	 *
	 * @param id The unique ID of the pet to be deleted.
	 * @return Response indicating whether the pet deletion was successful.
	 */
	public static Response deletePet(int id) {
		String deletePetUrl = getUrl().getString("deletePet");

		Response response = given()
				.header("api_key", "special-key")
				.accept(ContentType.JSON)
				.pathParam("petId", id)
				.when()
				.delete(deletePetUrl);

		return response;
	}

}
