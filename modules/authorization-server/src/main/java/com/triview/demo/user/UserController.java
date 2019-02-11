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
@RequestMapping("/api/users")
public class UserController {

    private static final String ADMIN_AUTHORITY = "ADMIN";

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Find a user by their email address.  Admin users are allowed to find anyone, while regular users may only
     * lookup themselves.
     *
     * @param email          search value
     * @param authentication OAuth2Authentication authentication object
     * @return the found user
     * @throws UserNotFoundException if no user with the given email address exists.
     */
    @GetMapping("/findByEmail")
    @PreAuthorize("hasAuthority('ADMIN') || (authentication.principal == #email)")
    User findByEmail(@RequestParam String email, OAuth2Authentication authentication) {
        return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("email=" + email));
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN') || (returnObject != null && returnObject.email == authentication.principal)")
    User one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("id=" + id.toString()));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    User create(@Valid @RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    void delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException("id=" + id.toString());
        }
    }

    @GetMapping
    Page<User> allUsers(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable,
                        OAuth2Authentication authentication) {

        if (!isAdmin(authentication)) {
            String auth = (String) authentication.getPrincipal();
            return repository.findAllByEmail(auth, pageable);
        } else {
            return repository.findAll(pageable);
        }
    }

    private boolean isAdmin(OAuth2Authentication authentication) {
        Collection<GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().anyMatch(ga -> ga.getAuthority().equals(ADMIN_AUTHORITY));
    }

}
