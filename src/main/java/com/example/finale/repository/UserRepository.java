
package com.example.finale.repository;

import com.example.finale.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   User findFirstByEmail(String email);
   User findUserById(Long id);

}
