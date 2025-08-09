package com.user.repository;

import com.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public Optional<UserEntity> findByUserId(String userId);
    public Optional<UserEntity> findByFirstNameOrLastName(String firstName, String lastName);

    // JPQL/HQL Query
    @Query("FROM UserEntity u WHERE u.firstName = :firstName")
    List<UserEntity> findByFirstNameHQL(@Param("firstName") String firstName);

    // Native SQL Query
    @Query(value = "SELECT * FROM users WHERE email LIKE %:email", nativeQuery = true)
    Optional<UserEntity> findByEmailSQL(@Param("email") String email);
}
