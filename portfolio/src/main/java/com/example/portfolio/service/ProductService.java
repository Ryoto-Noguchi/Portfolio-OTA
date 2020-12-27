package com.example.portfolio.service;

import java.util.List;

import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.entity.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductService {

  @Autowired
  ProductRepository productRepos;

	public List<Products> findAll() {
		return productRepos.findAll();
	}

}
