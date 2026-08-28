package com.bridgelabz.model;

import java.time.LocalDateTime;

public class Greeting {
    private int id;
    private String userName;
    private String message;
    private LocalDateTime createdDate;
    public Greeting()
    {

    }
    public Greeting(int id, String userName, String message, LocalDateTime createdDate) {
        this.id = id;
        this.userName = userName;
        this.message = message;
        this.createdDate = createdDate;
    }

    public Greeting(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
