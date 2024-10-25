package com.project.todoList.repository;

import com.project.todoList.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemsRepository extends JpaRepository<TodoItem, Long> {

}
