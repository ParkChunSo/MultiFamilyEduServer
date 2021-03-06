package com.cosmos.multifamily.repository;


import com.cosmos.multifamily.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *UserRepository
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserid(String userid);

}
