package com.novelitech.course.repositories;

import com.novelitech.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// O Spring Data JPA já tem uma implementação padrão para essa interface, então eu não preciso fazer
// uma implementação por mim mesmo
public interface UserRepository extends JpaRepository<User, Long> {

}
