package com.cinema.security;

import com.cinema.exception.AuthenticationException;
import com.cinema.lib.Dao;
import com.cinema.lib.Inject;
import com.cinema.model.User;
import com.cinema.service.UserService;
import com.cinema.util.HashUtil;

@Dao
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        return userService.findByEmail(email)
                .filter(u -> HashUtil.hashPassword(password, u.getSalt()).equals(u.getPassword()))
                .orElseThrow(() -> new AuthenticationException("Incorrect email or password"));
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        return userService.add(user);
    }
}
