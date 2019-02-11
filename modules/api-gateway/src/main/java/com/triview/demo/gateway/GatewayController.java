package com.triview.demo.gateway;

import com.triview.demo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GatewayController {

//    @PostMapping("/login")
//    User register(@RequestParam String email, @RequestParam String password) {
//        User user = new User(email, passwordEncoder.encode(password));
//        return repository.save(user);
//    }
//
//    @GetMapping("/findUserByEmail")
//    @PreAuthorize("hasAuthority('ADMIN') || (authentication.principal == #email)")
//    User findByEmail(@RequestParam String email, OAuth2Authentication authentication) {
//        return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("email=" + email));
//    }
//
//    /**
//     * Allow someone to register as a new user.
//     *
//     * @param email    the email address / username to identify the user
//     * @param password the desired password for the user
//     * @return the newly registered user.
//     */
//    @PostMapping("/register")
//    User register(@RequestParam String email, @RequestParam String password) {
//        User user = new User(email, passwordEncoder.encode(password));
//        return repository.save(user);
//    }


}
