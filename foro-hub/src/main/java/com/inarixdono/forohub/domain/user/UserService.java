package com.inarixdono.forohub.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inarixdono.forohub.infra.exception.EntityNotFoundException;
import com.inarixdono.forohub.infra.exception.PasswordsDoNotMatchException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder enconder;

    public User createUser(NewUserDTO userDTO) {
        if (!userDTO.password().equals(userDTO.confirmPassword())) {
            throw new PasswordsDoNotMatchException();
        }
        String password = enconder.encode(userDTO.password());
        User user = new User(userDTO, password);
        return repository.save(user);
    }

    public User getUser(Integer id) {
        return repository.findByIdAndStatusTrue(id).orElseThrow(() -> new EntityNotFoundException());
    }
}
