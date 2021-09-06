package am.sevo.springrest.restcontroller;


import am.sevo.springrest.model.User;
import am.sevo.springrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable(name = "id") int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
          return  ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("User with " + id + " id not found");
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable(name = "id") int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping()
    public ResponseEntity saveUser(@RequestBody User user) {
        userRepository.save(user);
       return ResponseEntity.ok("created");
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody User requestUser){
        List<User> userList = userRepository.findAll();
        for (User user: userList) {
            if (user.getEmail().equals(requestUser.getEmail())){
                if (user.getPassword().equals(requestUser.getPassword())){
                    return ResponseEntity.ok("login successfull" + " hello " + user.getName());
                }
                return ResponseEntity.ok("Your password is Incorrect");
            }
        }
        return ResponseEntity.ok("User is Exist");

    }

}