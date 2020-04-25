package com.aleksandrakrzak.shop.repository.jpa;

import com.aleksandrakrzak.shop.domain.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // jpa dzidzeczy po pagingandsorting repositoryktore dziedziczy po crudrepository ktore dziedziczy po repository

    Optional<User> findByMail(String mail); // daje optional bo zabezpiecza mnie przed nullPointerException

}
