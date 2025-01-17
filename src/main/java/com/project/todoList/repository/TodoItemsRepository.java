package com.project.todoList.repository;

import com.project.todoList.entity.TodoItem;
import com.project.todoList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemsRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByUser(User user);
}
