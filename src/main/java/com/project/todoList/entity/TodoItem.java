package com.project.todoList.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "todo_items_table")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String title;
    private String information;
    private String date;

    private String isCompleted;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TodoItem(Long id, String title, String information, String date, String isCompleted, User user) {
        this.id = id;
        this.title = title;
        this.information = information;
        this.date = date;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    public TodoItem() {
    }
}
