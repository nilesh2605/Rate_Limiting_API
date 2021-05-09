package com.ratelimit.ratelimit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratelimit.ratelimit.entities.User;

public interface UserDao extends JpaRepository<User, Long>{

}
