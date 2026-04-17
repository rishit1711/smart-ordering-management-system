package com.OnlineOrderSystem.SOOMS.repository;

import com.OnlineOrderSystem.SOOMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    User findByEmail(String email);
//
//
//    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
