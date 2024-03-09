package br.com.api.resources;

import br.com.api.domain.User;
import br.com.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById (@PathVariable Integer id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

//    @PostMapping(value = "/criar")
//    public void  criar (@RequestBody User user) {
//
//    }
}
