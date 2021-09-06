package am.sevo.springrest.repository;

import am.sevo.springrest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

   Optional<Book> findByName(String name);

}
