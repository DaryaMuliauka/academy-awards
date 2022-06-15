package com.muliavka.academyawards.dao.repository;

import com.muliavka.academyawards.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUserName(String userName);
}
