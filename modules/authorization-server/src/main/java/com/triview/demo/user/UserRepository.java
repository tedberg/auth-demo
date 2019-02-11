package com.triview.demo.user;

import com.triview.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    /**
     * Find a single user by their email address.
     *
     * @param email search value.
     * @return Optional containing the found user if they exist.
     */
    Optional<User> findByEmail(String email);

    /**
     * Find all users by email address
     *
     * @param email    search value.
     * @param pageable object for controlling pagination
     * @return a Page of users.
     */
    Page<User> findAllByEmail(String email, Pageable pageable);

}
