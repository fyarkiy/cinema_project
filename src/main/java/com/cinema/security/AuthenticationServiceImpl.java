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
    UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        return userService.findByEmail(email)
                .filter(u -> HashUtil.hashedPassword(password, u.getSalt()).equals(u.getPassword()))
                .orElseThrow(() -> new AuthenticationException("Incorrect email or password"));
    }

    @Override
    public User register(String email, String password) {
        byte[] salt = HashUtil.getSalt();
        User user = new User();
        user.setPassword(HashUtil.hashedPassword(password,salt));
        user.setEmail(email);
        user.setSalt(salt);
        return userService.add(user);
    }
}
