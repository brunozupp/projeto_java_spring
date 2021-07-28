package com.novelitech.course.repositories;

import com.novelitech.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// O Spring Data JPA já tem uma implementação padrão para essa interface, então eu não preciso fazer
// uma implementação por mim mesmo
//@Repository - Não é necessário essa anotação pois JpaRepository já possui esse registro como um componente do Spring
public interface UserRepository extends JpaRepository<User, Long> {

}
