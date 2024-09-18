package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	public Product save(Product product) {
		return repository.save(product);
	
	}
	
//	public Product update(Product product,Long productId) {
//		//one coming from front end
//		return repository.save(product); //saveupdate
//	
//	}
	
	public Product getById(Long productId) {
		Optional<Product> optional= repository.findById(productId);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	
	public List<Product> getAll(){
		return repository.findAll();
	}
	

	public Product update(Long productId, Product newproduct) {
		Optional<Product> optional = repository.findById(productId);
		if(optional.isPresent()) {
			
			Product product = optional.get();
			newproduct.setProductId(product.getProductId());
			return repository.save(newproduct);
			
		}
		else
			return null;
	}
	
	public boolean delete(Long productId) {
		Optional<Product> optional = repository.findById(productId);
		if(optional.isPresent()) {
			repository.delete(optional.get());
			return true;
		}
		else {
			return false;
		}
	}
}
