package com.shahin.TodoAPISpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private static List<Todo> todoList;

    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
    }


    @GetMapping("/todoList")
    public List<Todo> getTodos() {
        return todoList;
    }
}







// Controller is like an entrypoint, this is the first layer that collect client request
// it has two use cases first collecting the request and second is returning the response
// whatever method you define that returns any value that returned value automatically is going to be return in the response body of the http response object

// once you annotate you can automatically start using http response object

//@RestController  =  @Controller + @ResponseBody