//***************************************************************
// Copyright (C) 2013 Baraan Soft
//***************************************************************
//      Project Name : MvcExample
//      Created At   : 27-Jan-2014
//      Author       : prasad
//      Class Name   : Product.java
//
//***************************************************************
// Class Description :
//
//***************************************************************
package com.models;

/**
 * @author prasad
 *
 */
public class Product {
	String productName;
	String productDesc;
	String productPrice;
	
	public Product(String productName,String productDesc,String productPrice) {
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
	}
	
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}
	
	/**
	 * @return the productPrice
	 */
	public String getProductPrice() {
		return productPrice;
	}
}
