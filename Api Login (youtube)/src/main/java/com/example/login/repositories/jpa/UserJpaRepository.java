package com.example.login.repositories.jpa;

import com.example.login.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserNameAndPassword(String userName, String password);
    List<UserEntity> findAllByIsActive(boolean active);

    UserEntity findByUserName(String userName);

}
