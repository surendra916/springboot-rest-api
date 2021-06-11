package com.cruddemo.crudapp.controller;
import com.cruddemo.crudapp.Entity.Todo;
import com.cruddemo.crudapp.Repository.TodosRepo;
import com.cruddemo.crudapp.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "https://ssr-context-api-todo-app.netlify.app")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodosRepo todosRepo;
    
    @GetMapping("/")
    public String sample(){
        return "Automatic Deployment Succeeded!!!!";
    }

    @GetMapping("/getAllTodos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        List allTodos = todoService.getTodos();

        return new ResponseEntity<List<Todo>>(allTodos,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        try{
            System.out.println("Request body is "+ todo.toString());
            Todo createdTodo = todoService.addTodo(todo);
            return new ResponseEntity<Todo>(createdTodo,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable long todoId,@RequestBody Todo todo){

        Optional<Todo> old_todo = todosRepo.findById(todoId);
        if (!old_todo.isPresent())
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        else {
            Todo updatedTodo = old_todo.get();
            updatedTodo.setTodoDescription(todo.getTodoDescription());
            updatedTodo.setIsCompleted(todo.getIsCompleted());
            return new ResponseEntity<Todo>(todosRepo.save(updatedTodo), HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("delete/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long todoId){

            try{
                if (todoId > 0 && todosRepo.findById(todoId) != null) {
                    todosRepo.deleteById(todoId);
                    return new ResponseEntity<>("Deleted Successfuly", HttpStatus.OK);
                }

            }catch(Exception e){
                return new ResponseEntity<>("User Not found with that ID ",HttpStatus.NOT_FOUND);
            }
        return null;
    }

}
