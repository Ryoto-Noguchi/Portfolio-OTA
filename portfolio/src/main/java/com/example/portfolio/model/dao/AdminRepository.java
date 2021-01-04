package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByAdminNameAndPassword(@Param("adminName") String adminName, @Param("password") String password);

	@Modifying
	@Query(value = "UPDATE admin SET admin_name = :adminName, password = :password WHERE  id = :id" , nativeQuery = true)
	void updateAdmin(@Param("id") int id, @Param("adminName") String adminName, @Param("password") String password);

}
