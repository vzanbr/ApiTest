package br.com.api.service.impl;

import br.com.api.domain.User;
import br.com.api.domain.dto.UserDTO;
import br.com.api.repository.UserRepository;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void create() {
    }

    @Test
    void update() {
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