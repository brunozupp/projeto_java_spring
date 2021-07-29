package com.novelitech.course.services;

import com.novelitech.course.entities.User;
import com.novelitech.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        return obj.get();
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User obj) {
        // Instancia pra mim um usuário, mas esse método não vai no banco de dados ainda, vai deixar apenas
        // um objeto monitorado pelo JPA para eu trabalhar com ele e em seguida efetuar uma operação no banco de
        // dados. Essa é a diferença entre getById e findById
        User entity = repository.getById(id);

        updateData(entity,obj);

        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }


}
