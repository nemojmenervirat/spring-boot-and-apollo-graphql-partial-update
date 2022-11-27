package com.github.nemojmenervirat.springbootapp.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.nemojmenervirat.springbootapp.model.request.UpdateUserRequest;
import com.github.nemojmenervirat.springbootapp.model.response.UserResponse;
import com.github.nemojmenervirat.springbootapp.service.UserService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<UserResponse> getAll() {
    return userService.getAll();
  }

  @PatchMapping(path = "/{id}")
  public UserResponse patch(@PathVariable UUID id, @RequestBody JsonPatch patch) {
    return userService.patch(id, patch);
  }

  @PutMapping
  public UserResponse partialUpdate(@Valid @RequestBody UpdateUserRequest request) {
    return userService.partialUpdate(request);
  }

}
