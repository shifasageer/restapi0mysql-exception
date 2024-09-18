package com.example.demo.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.exceptionhandlers.InvalidValueException;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("/product/api.1.0")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@PostMapping
	@RequestMapping(path="/create", 
					consumes=MediaType.APPLICATION_JSON_VALUE,
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createProduct(@RequestBody Product productReq){
		
		Product newProduct =service.save(productReq);
		return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
		
	}
	
	@GetMapping
	@RequestMapping(path="/getall")
	public ResponseEntity<List<Product>> getAll(){
		List<Product> newList = service.getAll();
		return new ResponseEntity<List<Product>>(newList,HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Product> fetch(@PathVariable Long id){
			
		return  ResponseEntity.ok(service.getById(id));
				
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Product product){
		Product newprod = service.update(id, product);
		if(newprod!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(newprod);
			
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found");
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		boolean status = service.delete(id);
		if (status==true) {
			return new ResponseEntity<>("Deleted succefully",HttpStatus.OK);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product found");
		}
	}
	
	
	@GetMapping("/test")
	public String message(String str) throws Exception{
		str="acs1$@3";
		if(str==null) {
			throw new NullPointerException("value not provded");
			}
		boolean isNumeric= str.chars().anyMatch(x-> Character.isDigit(x));
		if(isNumeric) {
			throw new InvalidValueException("Expected String found int");		}
		if(str.length()<8) {
			throw new Exception("Lnegth < 8");
		}
		   
		else	
			return "helo";

		}
	
}

