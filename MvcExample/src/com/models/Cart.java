//***************************************************************
// Copyright (C) 2013 Baraan Soft
//***************************************************************
//      Project Name : MvcExample
//      Created At   : 27-Jan-2014
//      Author       : prasad
//      Class Name   : cart.java
//
//***************************************************************
// Class Description :
//
//***************************************************************
package com.models;

import java.util.ArrayList;

/**
 * @author prasad
 *
 */
public class Cart {
	ArrayList<Product> products = new ArrayList<Product>();
	
	public Product getProduct(int position) {
		return products.get(position);
	}
	
	public void setProduct(Product product) {
		products.add(product);
	}
	
	public int getCartSize() {
		return products.size();
	}
	
	public boolean isCartHaving(Product product) {
		return products.contains(product);
	}
}
