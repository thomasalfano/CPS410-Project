package dev._0.mindracers.example.testdatamysql;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
   
}
