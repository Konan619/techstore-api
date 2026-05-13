package cl.techstore.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techstore.api.dto.LoginRequest;
import cl.techstore.api.dto.LoginResponse;
import cl.techstore.api.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // Usuario fijo en memoria
        String username = "admin";
        String password = "admin123";

        if (!username.equals(request.getUsername()) ||
                !password.equals(request.getPassword())) {

            return ResponseEntity
                    .badRequest()
                    .body("Credenciales incorrectas");
        }

        String token = jwtUtil.generateToken(username);

        LoginResponse response = new LoginResponse(
                token,
                "Bearer",
                jwtUtil.getExpiration()
        );

        return ResponseEntity.ok(response);
    }
}