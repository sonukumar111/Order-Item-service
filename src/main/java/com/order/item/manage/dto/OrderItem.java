package com.order.item.manage.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Order_Item")
public class OrderItem implements Serializable{

	private static final long serialVersionUID = -4295822241504477672L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer productCode;
	private String productName;
	private Integer quantity;
	private String user;
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItem(Integer productCode, String productName, Integer quantity, String user) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.user = user;
	}
	public Integer getProductCode() {
		return productCode;
	}
	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "OrderItem [productCode=" + productCode + ", productName=" + productName + ", quantity=" + quantity
				+ ", user=" + user + "]";
	}
	
	
}
