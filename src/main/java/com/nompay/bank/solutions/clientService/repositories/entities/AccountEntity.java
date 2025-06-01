package com.nompay.bank.solutions.clientService.repositories.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "bank_account",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"currency"})
        }
)
public class AccountEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_user_id", nullable = false)
    private final UserEntity ownerUser;

    @OneToMany(mappedBy = "blockedAccounts", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BlockedAccountsEntity> blockedAccounts;

    @OneToMany(mappedBy = "blockedByAccount", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BlockedAccountsEntity> blockedByAccount;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String currency;

    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public AccountEntity(String name, String email, UserEntity ownerUser, Integer balance, String currency) {
        this.name = name;
        this.email = email;
        this.ownerUser = ownerUser;
        this.balance = balance;
        this.currency = currency;
    }

    @PrePersist
    protected void onCreate() {
        this.createDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BlockedAccountsEntity> getBlockedUsers() {
        return blockedAccounts;
    }

    public void setBlockedUsers(List<BlockedAccountsEntity> blockedUsers) {
        this.blockedAccounts = blockedUsers;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<BlockedAccountsEntity> getBlockedAccounts() {
        return blockedAccounts;
    }

    public void setBlockedAccounts(List<BlockedAccountsEntity> blockedAccounts) {
        this.blockedAccounts = blockedAccounts;
    }

    public List<BlockedAccountsEntity> getBlockedByAccounts() {
        return blockedByAccount;
    }

    public void setBlockedByAccounts(List<BlockedAccountsEntity> blockedByAccounts) {
        this.blockedByAccount = blockedByAccounts;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public UserEntity getOwnerUser() {
        return ownerUser;
    }

    public List<BlockedAccountsEntity> getBlockedByAccount() {
        return blockedByAccount;
    }
}
