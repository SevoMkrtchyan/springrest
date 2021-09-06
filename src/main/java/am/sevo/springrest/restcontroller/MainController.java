package am.sevo.springrest.restcontroller;


import am.sevo.springrest.repository.AuthorRepository;
import am.sevo.springrest.repository.BookRepository;
import am.sevo.springrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/main")
public class MainController {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/printAuthors")
    public ResponseEntity printAllAuthors(){
        return ResponseEntity.ok(authorRepository.findAll());
    }
    @GetMapping(value = "/printBooks")
    public ResponseEntity printAllBooks(){
        return ResponseEntity.ok(bookRepository.findAll());
    }

}
