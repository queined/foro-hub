package com.inarixdono.forohub.domain.user;

public record UserDTO(Integer id, String name, String username) {
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getUsername());
    }
}
