package com.nompay.bank.solutions.clientService.repositories.entities;

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

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_user_id", nullable = false)
    private UserEntity ownerUser;

    @OneToMany(mappedBy = "blockedAccount", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BlockedAccountsEntity> blockedAccounts;

    @OneToMany(mappedBy = "blockedByAccount", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BlockedAccountsEntity> blockedByAccounts;

    @Column(nullable = false)
    private Integer balance;

    @Column(nullable = false)
    private String currency;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public AccountEntity() {
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

    public Integer getBalance() {
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
        return blockedByAccounts;
    }

    public void setBlockedByAccounts(List<BlockedAccountsEntity> blockedByAccounts) {
        this.blockedByAccounts = blockedByAccounts;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
