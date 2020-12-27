package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Categories;
import com.example.portfolio.model.entity.Destinations;
import com.example.portfolio.model.entity.Products;
import com.example.portfolio.model.entity.Testimonials;
import com.example.portfolio.service.CategoryService;
import com.example.portfolio.service.DestinationService;
import com.example.portfolio.service.ProductService;
import com.example.portfolio.service.TestimonialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/portfolio")
public class IndexController {

  @Autowired
  CategoryService categoryService;

  @Autowired
  DestinationService destinationService;

  @Autowired
  ProductService productService;

  @Autowired
  TestimonialService testimonialService;

  @GetMapping(value = "")
  public String index(Model m) {
    List<Products> products = productService.findAll();
    List<Categories> categories = categoryService.findAll();
    for (Categories category : categories) {
      System.out.println("取得したカテゴリID : " + category.getCategoryId());
      System.out.println("取得したカテゴリ名 : " + category.getCategoryName());
    }
    List<Destinations> destinations = destinationService.findAll();
    for (Destinations destination : destinations) {
      System.out.println("取得したカテゴリ名 : " + destination.getDestinationName());
      System.out.println("取得したカテゴリID : " + destination.getDestinationImage());
    }
    List<Testimonials> testimonials = testimonialService.findAll();
    m.addAttribute("products", products);
    m.addAttribute("categories", categories);
    m.addAttribute("destinations", destinations);
    m.addAttribute("testimonials", testimonials);
    return "index";
  }




}
