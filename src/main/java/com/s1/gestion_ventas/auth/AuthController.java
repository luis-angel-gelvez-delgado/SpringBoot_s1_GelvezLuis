package com.s1.gestion_ventas.auth;

import com.s1.gestion_ventas.config.JwtService;
import com.s1.gestion_ventas.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("1234")) {
            return jwtService.generateToken(username);
        }
        throw new BusinessRuleException("Credenciales invalidas");
    }
}