package am.sevo.springrest.service;

import am.sevo.springrest.entity.Book;

import java.util.List;

public interface BookService {


    List<Book> findAll();

    boolean saveBook(Book book);

    Book getBookById(int id);

    boolean deleteBookById(int id);

    List<Book> findBooksByAuthorId(int id);

}
