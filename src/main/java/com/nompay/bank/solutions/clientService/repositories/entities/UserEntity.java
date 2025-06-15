package com.nompay.bank.solutions.clientService.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "username"})
    }
)
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private long id;

  @NotBlank
  @Size(min = 5, max = 30, message = "Email must be at least 5 characters long, less than 30")
  @Column(nullable = false)
  private String email;

  @NotBlank
  @Size(min = 3, max = 25, message = "Name must be at least 3 characters long, less than 25")
  @Column(nullable = false)
  private String name;

  @NotBlank
  @Size(min = 2, max = 25, message = "Surname must be at least 5 characters long, less than 25")
  @Column(nullable = false)
  private String surname;

  @NotBlank
  @Size(min = 5, max = 25, message = "Username must be at least 5 characters long, less than 25")
  @Column(nullable = false)
  private String username;

  @NotBlank
  @Size(min = 5, max = 35, message = "Password must be at least 5 characters long, less than 35")
  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "ownerUser", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<AccountEntity> Accounts;

  @Column(name = "create_date", updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  public UserEntity(String email, String name, String surname, String password, List<AccountEntity> accounts) {
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.password = password;
    Accounts = accounts;
  }

  public UserEntity() {
  }

  @PrePersist
  protected void onCreate() {
    this.createDate = new Date();
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAccounts(List<AccountEntity> accounts) {
    Accounts = accounts;
  }

  public long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getPassword() {
    return password;
  }

  public List<AccountEntity> getAccounts() {
    return Accounts;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
