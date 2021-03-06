//***************************************************************
// Copyright (C) 2013 Baraan Soft
//***************************************************************
//      Project Name : MvcExample
//      Created At   : 27-Jan-2014
//      Author       : prasad
//      Class Name   : Controller.java
//
//***************************************************************
// Class Description :
//
//***************************************************************
package com.view;

import java.util.ArrayList;

import com.models.Cart;
import com.models.Product;

import android.app.Application;

/**
 * @author prasad
 * 
 */
public class Controller extends Application {
	private ArrayList<Product> myProducts = new ArrayList<Product>();
	private Cart myCart = new Cart();

	/**
	 * Returns the product in the cart.
	 */
	public Product getProduct(int index) {
		return myProducts.get(index);
	}

	/**
	 * Adds the product to the cart.
	 */
	public void setProduct(Product product) {
		myProducts.add(product);
	}

	/**
	 * @return the myCart
	 */
	public Cart getMyCart() {
		return myCart;
	}

	/**
	 * Returns the size of the cart.
	 */
	public int getProductCount() {
		return myProducts.size();
	}
}
