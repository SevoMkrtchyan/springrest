package am.sevo.springrest.restcontroller;


import am.sevo.springrest.model.Author;
import am.sevo.springrest.model.Book;
import am.sevo.springrest.repository.AuthorRepository;
import am.sevo.springrest.repository.BookRepository;
import am.sevo.springrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

   @Autowired
   private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/saveAuthors")
    public ResponseEntity saveAuthors(@RequestBody Author author){
        authorRepository.save(author);
       return ResponseEntity.ok("Author " + author.getName() + " " +
               author.getSurname() + " has successfully added");
    }
    @PostMapping("/saveBooks")
    public ResponseEntity saveBooks(@RequestBody Book book){
        bookRepository.save(book);
        return ResponseEntity.ok("Book " + book.getName() + " which author is " +
                authorRepository.getById(book.getAuthor().getId()).getName() + " " +
                authorRepository.getById(book.getAuthor().getId()).getSurname() +
                " has successfully added");
    }

    @DeleteMapping(value = "/deleteAuthor/{id}")
    public ResponseEntity deleteAuthorById(@PathVariable (name = "id")int id){
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()){
            return ResponseEntity.ok("Author with " + author.get().getId()
            + " id has already removed or not added in list");
        }
        List<Book> bookList = bookRepository.findAll();
        for (Book book:bookList) {
            if (book.getAuthor().getId() == id){
                bookRepository.delete(book);
            }
        }
        authorRepository.delete(author.get());
        return ResponseEntity.ok("Author which name is " +
                author.get().getName() + " successfully deleted from Authors List");
    }

    @DeleteMapping(value = "/deleteBook/{id}")
    public ResponseEntity deleteBookById(@PathVariable (name = "id")int id){
        Book book = bookRepository.getById(id);
        bookRepository.delete(book);
        return ResponseEntity.ok("Book "+ book.getName() +
                " which Author is " +
                book.getAuthor().getName() + " " +book.getAuthor().getSurname() +
                " successfully deleted from Books List");
    }

    @GetMapping(value = "/author/{id}")
    public ResponseEntity getAuthorById(@PathVariable (name = "id")int id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (!authorOptional.isPresent()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Author with " + id + " id not found");
        }
        return ResponseEntity.ok(authorOptional);
    }

    @GetMapping(value = "/book/{id}")
    public ResponseEntity getBookById(@PathVariable (name = "id")int id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Author with " + id + " id not found");
        }
        return ResponseEntity.ok(bookOptional);
    }

    @GetMapping(value = "/bookByAuthor/{id}")
    public ResponseEntity getBookByAuthorId(@PathVariable (name = "id")int id){
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Author with " + id + " id not found");
        }
        List<Book> bookList = bookRepository.findAll();
        for (Book book:bookList) {
          if (book.getAuthor().getId() == id){
              return ResponseEntity.ok(book);
          }
        }
        return ResponseEntity.ok("Author with " + author.get().getId() + " id have not a Book" );
    }

}
