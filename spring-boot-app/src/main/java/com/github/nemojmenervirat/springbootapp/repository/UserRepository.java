package com.github.nemojmenervirat.springbootapp.repository;

import com.github.nemojmenervirat.springbootapp.model.entity.UserEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
