package com.triview.demo.user;

import com.triview.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@Validated
@RequestMapping("/api/register")
public class RegisterController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    RegisterController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // TODO: User model to handle minimum password strength
    /**
     * Allow someone to register as a new user.
     *
     * @param email    the email address / username to identify the user
     * @param password the desired password for the user
     * @return the newly registered user.
     */
    @PostMapping
    User register(@RequestParam String email, @RequestParam String password) {
        User user = new User(email, passwordEncoder.encode(password));
        return repository.save(user);
    }

}
