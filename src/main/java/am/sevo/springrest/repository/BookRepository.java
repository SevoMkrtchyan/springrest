package am.sevo.springrest.repository;

import am.sevo.springrest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {



}
