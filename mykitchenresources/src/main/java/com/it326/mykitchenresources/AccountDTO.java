package com.it326.mykitchenresources;

public class AccountDTO {

    private String name;
    private String username;
    private String password;
    // Constructors, getters, and setters
    // Constructor without parameters
    public AccountDTO() {
    }
    // Constructor with parameters
    public AccountDTO(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
    // Getters and setters for name, username, and password
    // (Omitted for brevity; you can generate these using your IDE or manually)
    
    // Getter and setter methods for 'name'
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // Getter and setter methods for 'username'
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    // Getter and setter methods for 'password'
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
