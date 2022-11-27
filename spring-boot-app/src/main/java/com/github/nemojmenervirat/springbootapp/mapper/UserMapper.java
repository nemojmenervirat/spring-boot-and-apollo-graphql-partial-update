package com.github.nemojmenervirat.springbootapp.mapper;

import com.github.nemojmenervirat.springbootapp.model.entity.UserEntity;
import com.github.nemojmenervirat.springbootapp.model.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

  UserResponse map(UserEntity entity);

}
