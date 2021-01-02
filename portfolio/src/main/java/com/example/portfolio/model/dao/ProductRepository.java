package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByDestinationId(int destinationId);

	@Query(value = "SELECT p.product_id, p.product_image, p.product_name " +
									"FROM products AS p " +
									// "INNER JOIN categories AS c ON p.category_id = c.category_id " +
									// "INNER JOIN destinations AS d ON p.destination_id = d.destination_id " +
									"WHERE p.product_name LIKE concat('%', :str, '%') " +
									// "OR c.category_name LIKE concat('%', :str, '%') " +
									// "OR d.destination_name LIKE concat('%', :str, '%') " +
									"ORDER BY p.product_id " +
									"LIMIT :records OFFSET :start", nativeQuery = true)
	Product fetchProduct(String str, Integer records, Integer start);

}
