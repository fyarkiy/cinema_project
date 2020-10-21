package com.cinema.security;

import com.cinema.exception.AuthenticationException;
import com.cinema.lib.Dao;
import com.cinema.lib.Inject;
import com.cinema.model.User;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.util.HashUtil;
import org.apache.log4j.Logger;

@Dao
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

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
        logger.info("Registration of User with e-mail " + email + " was started");
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
