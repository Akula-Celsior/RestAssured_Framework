package api.endpoints;

import java.util.ResourceBundle;
import static io.restassured.RestAssured.*;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

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
	 * Place an order for a pet by sending a POST request.
	 *
	 * @param payload The order details to be placed.
	 * @return Response containing the order confirmation details.
	 */
	public static Response placeOrder(Store payload) {
		String placeOrderUrl = getUrl().getString("placeOrder");

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(placeOrderUrl);

		return response;
	}

	/**
	 * Find purchase order by ID using a GET request.
	 *
	 * @param id The unique ID of the order.
	 * @return Response containing order details.
	 */
	public static Response findOrder(int id) {
		String findOrderUrl = getUrl().getString("getOrder");

		Response response = given()
				.pathParam("orderId", id)
				.when()
				.get(findOrderUrl);

		return response;
	}

	/**
	 * Delete purchase order by ID using a DELETE request.
	 *
	 * @param id The unique ID of the order to be deleted.
	 * @return Response indicating whether the order deletion was successful.
	 */
	public static Response deleteOrder(int id) {
		String deleteOrderUrl = getUrl().getString("deleteOrder");

		Response response = given()
				.pathParam("orderId", id)
				.when()
				.delete(deleteOrderUrl);

		return response;
	}

}
