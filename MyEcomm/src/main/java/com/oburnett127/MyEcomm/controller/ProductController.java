package com.oburnett127.MyEcomm.controller;

import com.oburnett127.MyEcomm.model.Account;
import com.oburnett127.MyEcomm.model.Product;
import com.oburnett127.MyEcomm.service.ProductService;
import com.oburnett127.MyEcomm.util.ServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public @ResponseBody Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public @ResponseBody Product getProduct(@PathVariable(value="id") Integer id) {
		return productService.getProduct(id);
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.PUT)
	public @ResponseBody Product updateProduct(@RequestBody Product Product) {
		return productService.updateProduct(Product);
	}
	
	@RequestMapping(value = "/product/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object delete(@PathVariable(value="id") Integer id) {
		productService.deleteProduct(id);
		return null;
	}
	

}