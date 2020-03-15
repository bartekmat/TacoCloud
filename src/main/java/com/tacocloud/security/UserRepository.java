package com.tacocloud.data;

import com.tacocloud.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserRepository, Long> {
    User findByUsername(String username);
}
