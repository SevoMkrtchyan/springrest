package am.sevo.springrest.service.impl;

import am.sevo.springrest.entity.Book;
import am.sevo.springrest.repository.BookRepository;
import am.sevo.springrest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public boolean saveBook(Book book) {
        if (!bookRepository.findByName(book.getName()).isPresent()) {
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public Book getBookById(int id) {
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public boolean deleteBookById(int id) {
        if (getBookById(id) != null) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> findBooksByAuthorId(int id) {
        List<Book> bookList = bookRepository.findAll();
        List<Book> books = new LinkedList<>();
        for (Book book : bookList) {
            if (book.getAuthor().getId() == id) {
                books.add(book);
            }
        }
        return books;
    }
}
