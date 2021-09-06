package am.sevo.springrest.endpoint;


import am.sevo.springrest.entity.Author;
import am.sevo.springrest.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/author")
@RequiredArgsConstructor
public class AuthorEndpoint {

    private final AuthorService authorService;

    @GetMapping()
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> saveAuthors(@RequestBody Author author) {
        if (authorService.saveAuthor(author)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable(name = "id") int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        authorService.deleteAuthorById(author.getId());
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable(name = "id") int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

}
