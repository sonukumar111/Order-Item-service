package com.order.item.manage.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.item.manage.dto.OrderItem;
import com.order.item.manage.repo.OrderItemRepository;

/**
 * this class is act's a transaction service class
 * for OrderItem repository 
 * */
@Service
@Transactional
public class OrderItemTransactionalService {
	
	@Autowired
	private OrderItemRepository itemRepository;
	
	/**
	 * 	this method act's an intermediate create service for OrderItem
	 * @param OrderItem
	 * 
	 * @return Integer
	 * */
	public Integer createOrderItem(OrderItem orderItem) {
		return itemRepository.save(orderItem).getProductCode();
	}
	
	/**
	 * 	this method act's an intermediate search service for OrderItem
	 * @param Integer
	 * 
	 * @return Optional<OrderItem>
	 * */
	public Optional<OrderItem> getOrderItem(Integer productCode) {
		return itemRepository.findById(productCode);
	}
	
	/**
	 * 	this method act's an intermediate search service for OrderItem based on user
	 * @param String
	 * 
	 * @return List<OrderItem>
	 * */
	public List<OrderItem> getItemByUser(String user){
		return itemRepository.findByUser(user);
	}
	/**
	 * 	this method act's an intermediate search service for fetching all the items list
	 * 
	 * 
	 * @return List<OrderItem>
	 * */
	public List<OrderItem> getItemsList(){
		return itemRepository.findAll();
	}
}
