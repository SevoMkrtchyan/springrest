package am.sevo.springrest.repository;

import am.sevo.springrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
