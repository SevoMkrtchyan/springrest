package am.sevo.springrest.endpoint;

import am.sevo.springrest.entity.Author;
import am.sevo.springrest.entity.Book;
import am.sevo.springrest.service.AuthorService;
import am.sevo.springrest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/book")
public class BookEndpoint {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping()
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        if (bookService.saveBook(book)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable(name = "id") int id) {
        if (bookService.deleteBookById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBookById(@PathVariable(name = "id") int id) {
        Book bookOptional = bookService.getBookById(id);
        if (bookOptional == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookOptional);
    }

    @GetMapping(value = "/bookByAuthor/{id}")
    public ResponseEntity<?> getBookByAuthorId(@PathVariable(name = "id") int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            return ResponseEntity.ok(bookService.findBooksByAuthorId(id));
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
