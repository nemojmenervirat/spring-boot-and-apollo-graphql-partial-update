package com.github.nemojmenervirat.springbootapp.model.response;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

  private UUID id;
  private String firstName;
  private String lastName;
  private String fullName;
  private String phoneNumber;
  private String aboutMe;

}
