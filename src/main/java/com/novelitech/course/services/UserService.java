package com.novelitech.course.services;

import com.novelitech.course.entities.User;
import com.novelitech.course.repositories.UserRepository;
import com.novelitech.course.services.exceptions.DatabaseException;
import com.novelitech.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

//@Component
@Service // O de cima funciona, mas coloca esse pra ficar mais semanticamente correto. Funciona igual o AddScoped do .NET
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        // Se não ter um valor, vai lançar a exceção
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {
        try {
            // Instancia pra mim um usuário, mas esse método não vai no banco de dados ainda, vai deixar apenas
            // um objeto monitorado pelo JPA para eu trabalhar com ele e em seguida efetuar uma operação no banco de
            // dados. Essa é a diferença entre getById e findById
            User entity = repository.getById(id);
            updateData(entity,obj);
            return repository.save(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }


}
