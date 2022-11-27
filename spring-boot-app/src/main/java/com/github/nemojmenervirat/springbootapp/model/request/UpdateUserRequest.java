package com.github.nemojmenervirat.springbootapp.model.request;

import com.github.nemojmenervirat.springbootapp.validation.NotEmptyOptional;
import java.util.Optional;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserRequest {

  @NotNull
  private UUID id;
  @NotEmptyOptional
  private Optional<String> firstName;
  @NotEmptyOptional
  private Optional<String> lastName;
  private Optional<String> phoneNumber;
  private Optional<String> aboutMe;

}
