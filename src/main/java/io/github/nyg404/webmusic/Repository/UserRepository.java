package io.github.nyg404.webmusic.Repository;

import io.github.nyg404.webmusic.UserAccount.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
