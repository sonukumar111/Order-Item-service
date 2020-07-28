package com.order.item.manage.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.item.manage.dto.OrderItem;
import com.order.item.manage.exception.OrderItemNotFound;
import com.order.item.manage.service.OrderItemTransactionalService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/order/item")
public class OrderItemEndPointsController {
	
	@Autowired
	private OrderItemTransactionalService service;
	
	@PostMapping("/create")
	@ApiOperation(value= "create order item")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "success fully created"),
	        @ApiResponse(code = 400, message = "Bad request")
	    })
	public ResponseEntity<String> createOrderItem(@Valid @RequestBody OrderItem orderItem){
		System.out.println(orderItem);
		Integer id=service.createOrderItem(orderItem);
		
		return new ResponseEntity<String>("Order item created with id "+id,HttpStatus.CREATED);
	}
	
	@ApiOperation(value= "get order item by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "success fully retrived"),
	        @ApiResponse(code = 404, message = "Order item not found")
	    })
	@GetMapping("/getOrderItem/{productCode}")
	public ResponseEntity<OrderItem> getOrderItem(@PathVariable Integer productCode){
		OrderItem product = service.getOrderItem(productCode)
			.orElseThrow(()->new OrderItemNotFound("Product not found with id "+productCode));
		return new ResponseEntity<OrderItem>(product,HttpStatus.OK);
	}
	//get item based on user
	@PostMapping("/getItem/{user}")
	public List<OrderItem> getItemByUser(@PathVariable String user){
		List<OrderItem> items=service.getItemByUser(user);
		System.out.println("list of item from 4001 :: "+items);
		 return items;
	}
	//get all the items
	@GetMapping("/getItems")
	public List<OrderItem> getItemList(){
		List<OrderItem> list=service.getItemsList();
		if(list.size()==0)
			throw new OrderItemNotFound("No Record Found");
		return list;
	}
		
}
