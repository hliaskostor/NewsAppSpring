package com.example.register.repository;

import com.example.register.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserNew extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users (username, first_name, last_name, email, password, check_pref) " +
            "VALUES (:username, :first_name, :last_name, :email, :password, :check_pref)", nativeQuery = true)
    int registerNewUser(@Param("username") String username,
                        @Param("first_name") String first_name,
                        @Param("last_name") String last_name,
                        @Param("email") String email,
                        @Param("password") String password,
                        @Param("check_pref") String checkpref);
}
