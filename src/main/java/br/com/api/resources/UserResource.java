package br.com.api.resources;

import br.com.api.domain.User;
import br.com.api.domain.dto.UserDTO;
import br.com.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById (@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll () {
        List<UserDTO> listDTO = userService.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
       return  ResponseEntity.ok().body(listDTO);
    }

//    @PostMapping(value = "/criar")
//    public void  criar (@RequestBody User user) {
//
//    }
}
