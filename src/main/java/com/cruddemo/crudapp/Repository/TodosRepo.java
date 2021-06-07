package com.cruddemo.crudapp.Repository;

import com.cruddemo.crudapp.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodosRepo extends JpaRepository<Todo,Long>{
}
