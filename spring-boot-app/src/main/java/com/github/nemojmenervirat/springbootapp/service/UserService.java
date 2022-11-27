package com.github.nemojmenervirat.springbootapp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.nemojmenervirat.springbootapp.mapper.UserMapper;
import com.github.nemojmenervirat.springbootapp.model.request.UpdateUserRequest;
import com.github.nemojmenervirat.springbootapp.model.entity.UserEntity;
import com.github.nemojmenervirat.springbootapp.model.response.UserResponse;
import com.github.nemojmenervirat.springbootapp.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final UserMapper mapper;
  private final ObjectMapper objectMapper = new ObjectMapper();

  {
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public List<UserResponse> getAll() {
    return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
  }

  public UserResponse partialUpdate(UpdateUserRequest request) {
    UUID userId = request.getId();
    UserEntity entity = repository.getReferenceById(userId);
    updateIfPresent(entity, UserEntity::setFirstName, request.getFirstName());
    updateIfPresent(entity, UserEntity::setLastName, request.getLastName());
    updateIfPresent(entity, UserEntity::setPhoneNumber, request.getPhoneNumber());
    updateIfPresent(entity, UserEntity::setAboutMe, request.getAboutMe());
    UserEntity resultEntity = repository.save(entity);
    return mapper.map(resultEntity);
  }

  private static <BEAN, FIELD_TYPE> void updateIfPresent(BEAN bean,
      BiConsumer<BEAN, FIELD_TYPE> setter,
      Optional<FIELD_TYPE> optionalValueProvider) {
    if (Objects.isNull(optionalValueProvider)) {
      return;
    }
    if (optionalValueProvider.isPresent()) {
      FIELD_TYPE value = optionalValueProvider.get();
      setter.accept(bean, value);
    } else {
      setter.accept(bean, null);
    }
  }

  public UserResponse patch(UUID id, JsonPatch patch) {
    UserEntity entity = repository.getReferenceById(id);
    entity = applyChanges(entity, patch);
    UserEntity resultEntity = repository.save(entity);
    return mapper.map(resultEntity);
  }

  @SneakyThrows
  private UserEntity applyChanges(UserEntity entity, JsonPatch patch) {
    JsonNode patched = patch.apply(objectMapper.convertValue(entity, JsonNode.class));
    return objectMapper.treeToValue(patched, UserEntity.class);
  }
}
