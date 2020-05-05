package com.foodproject.orders;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.foodproject.menu.Menu;
import com.foodproject.users.Users;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "")
public class OrdersController {
	
	@Autowired
	OrdersService service;

	@GetMapping("/user/{userId}/orders")
	Iterable<Orders> getAllUsers(@PathVariable Integer userId) {
		return service.getAllUserOrders(userId);
	}
	
	@GetMapping("/orders")
	Iterable<Orders> getAllOrders() {
		return service.getAllOrders();
	}

	@PostMapping("/add/orderOf/user/{userId}/food/{foodId}/restaurant/{restaurantId}/menu/{menuId}")
	String addUser(@RequestBody Orders order, 
				 @PathVariable Integer userId,
				 @PathVariable int foodId,
				 @PathVariable int restaurantId,
				 @PathVariable int menuId
				 ) {
		order.setUser(new Users(userId, "", "", "", "", "","",""));
		order.setMenu(new Menu(menuId, foodId, restaurantId));
		service.addOrder(order);
		
		return "Order ID "+order.getOrderId()+" was placed.";
	}
	
	@GetMapping("/orders/{orderId}")
	Optional<Orders> getOrderrById(@PathVariable int orderId) {
		return service.getOrdersById(orderId);
	}

	@PutMapping("/update/orderOf/user/{userId}/food/{foodId}/restaurant/{restaurantId}/menu/{menuId}")
	String updateOrder(@RequestBody Orders order, 
			 @PathVariable Integer userId,
			 @PathVariable int foodId,
			 @PathVariable int restaurantId,
			 @PathVariable int menuId
			) {

		order.setUser(new Users(userId, "", "", "", "", "","",""));
		order.setMenu(new Menu(menuId, foodId, restaurantId));
		service.updateOrder(order);
		return "Order ID "+order.getOrderId()+" was updated.";
	}

	@DeleteMapping(value = "/remove/orders/{id}")
	String deleteOrder(@PathVariable int id) {
		service.removeOrder(id);
		return "Order ID "+id+" was removed.";
	}	
	
	@GetMapping(value = "/get/userTotalAmount/{userId}")
	Double getUserTotalAmount(@PathVariable int userId) {
		return service.getUserTotalAmount(userId);
	}

}
