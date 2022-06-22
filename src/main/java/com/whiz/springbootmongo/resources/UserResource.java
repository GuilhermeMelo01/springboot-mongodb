package com.whiz.springbootmongo.resources;

import com.whiz.springbootmongo.domain.User;
import com.whiz.springbootmongo.dto.UserDTO;
import com.whiz.springbootmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<User>> findAll() {
//        List<User> list = userService.findAll();
//        return ResponseEntity.ok().body(list);
//    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = userService.findAll();
        List<UserDTO> userDTO = list.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(userDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
        User user = userService.fromDto(userDTO);
        userService.insert(user);
        //Cabecalho com a URI (endereco) do novo objeto criado "É uma boa pratica" - Nelio Alves
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
