package am.sevo.springrest.service;

import am.sevo.springrest.entity.Author;

import java.util.List;

public interface AuthorService {


    List<Author> findAll();

    boolean saveAuthor(Author author);

    Author getAuthorById(int id);

    boolean deleteAuthorById(int id);

    Author findByNameAndSurname(String name, String surname);
}
