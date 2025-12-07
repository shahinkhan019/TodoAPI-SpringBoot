package com.shahin.TodoAPISpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private String errMsg;

    private static List<Todo> todos = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(2);

    private static String error(String errMsg) {
        return "Not found";
    }

    public TodoController() {
        todos.add(new Todo(1, false, "Todo 1", 1));
        todos.add(new Todo(2, true, "Todo 2", 2));
    }


    @GetMapping("/")
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(todos); // returning the list of todos with HTTP status 200 OK

    }


    @PostMapping("/")
//    @ResponseStatus(HttpStatus.CREATED) we can you RequestEntity<> at the place of this

    public ResponseEntity<Todo> createTodos(@RequestBody Todo newTodo) {

        if(newTodo.getId() == 0) {
           newTodo.setId((idCounter.incrementAndGet()));
        }
        todos.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);

    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable Long todoId) { // here we are using @PathVariable annotation to extract the todoId from the URL path
        for (Todo todo : todos) {
            if (todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
//        return ResponseEntity.notFound().build(); // build method is used to create the ResponseEntity with 404 status code
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found"); // for custom error message
    }



//whatever the incoming request in the http request body should be accessible to this createTodos method and that is done by using
// @RequestBody annotation we paas it as a parameter to the method so that spring boot can automatically map the incoming json data to this newTodo object
//   @RequestBody annotation is used to bind the HTTP request body with a domain object in method parameter.
// In this case, it tells Spring to convert the incoming JSON data in the request body into a Todo object and assign it to the newTodo parameter.

}
//                             _________________________
//                              WORKING OF @GetMapping
//                             -------------------------
//@GetMapping("/todos") tells Spring: "Route HTTP GET requests for the path /todos to this method."
//@GetMapping("/todos")             It maps GET /todos to getTodos().
//public List<Todo> getTodos() {   -->  This method handles GET requests to /todos
//    return todos;

// as we mentioned @RestController then what happens is that whatever value we return from this method
// automatically converted to JSON and put into the response body of the HTTP response object

// we don't need to do any manual conversion to JSON because Spring Boot does that for us automatically



//                                           _________________________
//                                         -: WORKING OF @PostMapping :-
//                                           -------------------------
// @PostMapping("/todos") -> It maps POST /todos to addTodo().

//@ResponseStatus(HttpStatus.CREATED) -> this annotation is used to specify the HTTP status code that should be returned when the annotated method completes successfully.
// In this case, it indicates that a new resource has been created successfully.

//public Todo createTodos(@RequestBody Todo newTodo) -> I have created a post request body through postman request api in json format
// now it will hit my server and inside the server in the request body of the http request object i am having access to that json data

// if(newTodo.getId() == 0) { -> so here what i am doing is that i am taking that json data from the request body of the http
// request object and mapping it to this newTodo object automatically by using @RequestBody annotation
// newTodo.setId((idCounter.incrementAndGet())); --> then i am adding that newTodo object to my todos list and returning that newTodo object as a response







