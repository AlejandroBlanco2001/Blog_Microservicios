package com.login.user.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.login.user.usuario.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthContoller {

    private AuthService authService;

    public static class LoginForm {
        public String username;
        public String password;

        public LoginForm(){

        }
        public LoginForm(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public String toString() {
            return "LoginForm{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @Autowired
    public AuthContoller(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginForm loginForm){
        String hashedPass = authService.getUsernameByUsername(loginForm.username).get().getPassword();
        System.out.println(hashedPass);
        BCrypt.Result result = BCrypt.verifyer().verify(loginForm.password.toCharArray(), hashedPass);
        return result.verified == true ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User registerForm){
        registerForm.setPassword(BCrypt.withDefaults().hashToString(12, registerForm.getPassword().toCharArray()));
        System.out.println(registerForm.toString());
        boolean response = authService.createUser(registerForm);
        return response == true ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }
}
