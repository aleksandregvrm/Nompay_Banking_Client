package com.nompay.bank.solutions.clientService.repositories.entities;

import jakarta.persistence.*;

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
        @GeneratedValue
        private long id;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String surname;

        @Column(nullable = false)
        private String password;

        @OneToMany(mappedBy = "ownerUser", cascade = CascadeType.PERSIST, orphanRemoval = true)
        private List<AccountEntity> Accounts;

        @Column(name = "create_date")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createDate;

        public UserEntity(String email, String name, String surname, String password, List<AccountEntity> accounts) {
                this.email = email;
                this.name = name;
                this.surname = surname;
                this.password = password;
                Accounts = accounts;
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
}
