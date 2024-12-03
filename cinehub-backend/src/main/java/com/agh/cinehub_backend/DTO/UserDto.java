package com.agh.cinehub_backend.dto;

import com.agh.cinehub_backend.model.Role;

public record UserDto(Integer userId, Role role, String email, String firstName, String lastName) {
}
