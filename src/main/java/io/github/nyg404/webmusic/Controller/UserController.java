package io.github.nyg404.webmusic.Controller;

import io.github.nyg404.webmusic.Service.UserService;
import io.github.nyg404.webmusic.UserAccount.LoginRequest;
import io.github.nyg404.webmusic.UserAccount.User;
import io.github.nyg404.webmusic.Utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return "Пользователь успешно создан";
    }

    @GetMapping
    @RequestMapping("/getUser")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        return jwtUtil.generateToken(loginRequest.getEmail());
    }
}