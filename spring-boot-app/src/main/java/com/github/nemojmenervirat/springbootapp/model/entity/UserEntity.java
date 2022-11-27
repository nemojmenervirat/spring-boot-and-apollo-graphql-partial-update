package com.github.nemojmenervirat.springbootapp.model.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "app_user")
@ToString
public class UserEntity {

  @Id
  @Type(type = "uuid-char")
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Formula(value = " concat(first_name, ' ', last_name) ")
  private String fullName;

  @Column
  private String phoneNumber;

  @Column(length = 4000)
  private String aboutMe;
}
