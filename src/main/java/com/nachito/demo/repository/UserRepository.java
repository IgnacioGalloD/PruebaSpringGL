package com.nachito.demo.repository;

import com.nachito.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String elMail);

    //boolean existByEmail(String elMail);

    //List<User> findByNombreContainingOrderByEdadAsc(String nombre);
}
