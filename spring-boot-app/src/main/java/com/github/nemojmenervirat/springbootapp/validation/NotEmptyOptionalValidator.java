package com.github.nemojmenervirat.springbootapp.validation;

import java.util.Objects;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyOptionalValidator implements ConstraintValidator<NotEmptyOptional, Optional> {

  @Override
  public boolean isValid(Optional value, ConstraintValidatorContext context) {
    return Objects.isNull(value) || value.isPresent();
  }
}
