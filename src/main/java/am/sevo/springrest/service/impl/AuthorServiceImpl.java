package am.sevo.springrest.service.impl;

import am.sevo.springrest.entity.Author;
import am.sevo.springrest.entity.Book;
import am.sevo.springrest.repository.AuthorRepository;
import am.sevo.springrest.repository.BookRepository;
import am.sevo.springrest.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public boolean saveAuthor(Author author) {
        if (findByNameAndSurname(author.getName(), author.getSurname()) == null) {
            authorRepository.save(author);
            return true;
        }
        return false;
    }

    @Override
    public Author getAuthorById(int id) {
        Optional<Author> byId = authorRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public boolean deleteAuthorById(int id) {
        if (getAuthorById(id) != null) {
            List<Book> bookList = bookRepository.findAll();
            for (Book book : bookList) {
                if (book.getAuthor().getId() == id) {
                    bookRepository.delete(book);
                }
            }
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        Optional<Author> byNameAndSurname = authorRepository.findByNameAndSurname(name, surname);
        return byNameAndSurname.orElse(null);
    }
}
