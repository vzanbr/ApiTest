package br.com.api.service.impl;

import br.com.api.domain.User;
import br.com.api.domain.dto.UserDTO;
import br.com.api.repository.UserRepository;
import br.com.api.service.exeptions.DataIntegratyViolationException;
import br.com.api.service.exeptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class UserServiceIMPLTest {

    @InjectMocks
    private UserServiceIMPL service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User response = service.findById(user.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(user.getId(), response.getId());
        Assertions.assertEquals(user.getName(), response.getName());
        Assertions.assertEquals(user.getEmail(), response.getEmail());
    }
    @Test
    void whenFindByIdThenReturnAnyObjectNotFoundException() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            service.findById(user.getId());
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(User.class, response.get(0).getClass());

        Assertions.assertEquals(user.getId(), response.get(0).getId());
        Assertions.assertEquals(user.getName(), response.get(0).getName());
        Assertions.assertEquals(user.getEmail(), response.get(0).getEmail());
        Assertions.assertEquals(user.getPassword(), response.get(0).getPassword());
    }

    @Test
    void createSucess() {
        Mockito.when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(user.getId(), response.getId());
        Assertions.assertEquals(user.getName(), response.getName());
        Assertions.assertEquals(user.getEmail(), response.getEmail());
        Assertions.assertEquals(user.getPassword(), response.getPassword());
    }
    @Test
    void createNotSucess() {
        Mockito.when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception ex) {
            Assertions.assertEquals(DataIntegratyViolationException.class, ex.getClass());
            Assertions.assertEquals("Email já cadastro no sistemas", ex.getMessage());
        }
    }

    @Test
    void updateSucess() {
        Mockito.when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(user.getId(), response.getId());
        Assertions.assertEquals(user.getName(), response.getName());
        Assertions.assertEquals(user.getEmail(), response.getEmail());
        Assertions.assertEquals(user.getPassword(), response.getPassword());
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(1, "Gabriel", "gabriel@gmail.com", "123");
        userDTO = new UserDTO(1, "Gabriel", "gabriel@gmail.com");
        optionalUser = Optional.of(new User(1, "Gabriel", "gabriel@gmail.com", "123"));
    }
}