package com.example.portfolio.service;

import java.util.List;
import java.util.Optional;

import com.example.portfolio.model.dao.CategoryRepository;
import com.example.portfolio.model.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryService {

  @Autowired
  CategoryRepository categoryRepos;

  /**
   * カテゴリ全取得
   * @return List<Category> カテゴリ一覧
   */
	public List<Category> findAll() {
		return categoryRepos.findAllCategories();
	}

  /**
   * カテゴリ取得
   * @param categoryId カテゴリID
   * @return Category カテゴリ
   */
	public Category findById(int categoryId) {
    Optional<Category> result = categoryRepos.findById(categoryId);
    Category destination = result.get();
    return destination;
	}

}
