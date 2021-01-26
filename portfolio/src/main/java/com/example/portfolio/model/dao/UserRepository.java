package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.form.UserForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

	User findById(int userId);

	User findByUserNameAndPassword(String userName, String password);

	@Query(value = "SELECT count(user_name) FROM users WHERE user_name = :newUserName", nativeQuery = true)
	int findByUserName(@Param("newUserName") String newUserName);

	@Modifying
	@Query(value = "INSERT INTO users (user_name, family_name, first_name, email, password, gender) VALUES (:#{#u.userName}, :#{#u.familyName}, :#{#u.firstName}, :#{#u.email}, :#{#u.password}, :#{#u.gender})", nativeQuery = true)
	int insertUser(@Param("u") User newUser);

	@Modifying
	@Query(value = "UPDATE users SET user_img = :bytes WHERE id = :userId", nativeQuery = true)
	int updateUserImage( @Param("bytes") byte[] bytes,
												@Param("userId") int userId);

	@Modifying
	@Query(value = "UPDATE users SET user_name = :#{#u.userName}, family_name = :#{#u.familyName}, first_name = :#{#u.firstName}, email = :#{#u.email}, password = :#{#u.password} WHERE id = :#{#u.userId}", nativeQuery = true)
	void updateUser(@Param("u") UserForm userForm);

	@Query(value = "SELECT user_name FROM users WHERE id = :userId", nativeQuery = true)
	String findUserNameById(@Param("userId") int userId);

	@Query(value = "SELECT email FROM users WHERE id = :userId", nativeQuery = true)
	String findEmailById(@Param("userId") int userId);

}
