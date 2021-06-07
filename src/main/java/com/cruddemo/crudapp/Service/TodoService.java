package com.cruddemo.crudapp.Service;

import com.cruddemo.crudapp.Entity.Todo;
import com.cruddemo.crudapp.Repository.TodosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodosRepo todosRepo;

    public Todo addTodo(Todo todo) {
        return todosRepo.save(todo);
    }

    public List<Todo> getTodos() {
        return todosRepo.findAll();
    }

    public Todo updateTodoById(long todoId , Todo td){
        Optional<Todo> todo = todosRepo.findById(todoId);




        if (todo.isPresent()){
            return todosRepo.save(td);
        }else {
            return null;
        }

    }
}
