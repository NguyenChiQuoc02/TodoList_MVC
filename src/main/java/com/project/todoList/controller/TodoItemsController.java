package com.project.todoList.controller;

import com.project.todoList.entity.TodoItem;
import com.project.todoList.entity.User;
import com.project.todoList.repository.TodoItemsRepository;
import com.project.todoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TodoItemsController {

    @Autowired
    private TodoItemsRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping({ "/user/todoItemsList"})
    public ModelAndView getTodoItemsList(@AuthenticationPrincipal UserDetails userDetails) {
        User user = this.userRepository.findByEmail(userDetails.getUsername());
        List<TodoItem> todoItems = repository.findByUser(user);
        todoItems = todoItems.stream().filter(item -> item.getIsCompleted().equals("Not done")).collect(Collectors.toList());
        ModelAndView mav = new ModelAndView("todo-items-list");
        mav.addObject("todoItems", todoItems);
        return mav;
    }

    @GetMapping("/user/deleteItem")
    public String deleteItem(@RequestParam Long todoItemId) {
        repository.deleteById(todoItemId);
        return "redirect:/user/todoItemsList";
    }


    //Name of the item has to be the same as the one in addItem, so it will populate properly
    @GetMapping("user/updateItem")
    public ModelAndView updateItem(@RequestParam Long todoItemId) {
        TodoItem newItem = repository.findById(todoItemId).get();
        ModelAndView mav = new ModelAndView("add-item");
        mav.addObject("newItem", newItem);
        return mav;
    }

    @GetMapping("user/addItem")
    public ModelAndView getAddItem() {
        ModelAndView mav = new ModelAndView("add-item");
        TodoItem newItem = new TodoItem();
        mav.addObject("newItem", newItem);
        return mav;
    }

    @PostMapping("/user/saveItem")
    public String saveItem(@ModelAttribute TodoItem newItem, @AuthenticationPrincipal UserDetails userDetails) {
        User user = this.userRepository.findByEmail(userDetails.getUsername());
        newItem.setUser(user);
        newItem.setIsCompleted("Not done");
        repository.save(newItem);
        return "redirect:/user/todoItemsList";
    }

    @GetMapping("user/done")
    public String done(@RequestParam Long todoItemId) {
        TodoItem todoItem = repository.findById(todoItemId).get();
        todoItem.setIsCompleted("Completed on: " + LocalDateTime.now().toLocalDate());
        repository.save(todoItem);
        return "redirect:/user/todoItemsList";
    }

    @GetMapping("user/history")
    public ModelAndView history(@AuthenticationPrincipal UserDetails userDetails) {
        User user = this.userRepository.findByEmail(userDetails.getUsername());
        List<TodoItem> todoItems = repository.findByUser(user);
        todoItems = todoItems.stream().filter(item -> !item.getIsCompleted().equals("Not done")).collect(Collectors.toList());
        ModelAndView mav = new ModelAndView("todo-items-history");
        mav.addObject("todoItems", todoItems);
        return mav;
    }
}
