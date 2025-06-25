package com.nompay.bank.solutions.clientService.repositories.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "blocked_accounts")
public class BlockedAccountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "blocker_account_id", nullable = false)
    private AccountEntity blockedByAccount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "blocked_account_id", nullable = false)
    private AccountEntity blockedAccounts;

    @Column(name = "blocked_account_email", nullable = false)
    private String blockedUserEmail;

    @Column(name = "block_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockDate;

    public BlockedAccountsEntity(AccountEntity blockedByAccount, AccountEntity blockedAccount, String blockedUserEmail, Date blockDate) {
        this.blockedByAccount = blockedByAccount;
        this.blockedAccounts = blockedAccount;
        this.blockedUserEmail = blockedUserEmail;
        this.blockDate = blockDate;
    }

    @PrePersist
    protected void onCreate() {
        this.blockDate = new Date();
    }

    public BlockedAccountsEntity() {
    }

    public Long getId() {
        return id;
    }

    public AccountEntity getBlockedByAccount() {
        return blockedByAccount;
    }

    public AccountEntity getBlockedAccounts() {
        return blockedAccounts;
    }

    public Date getBlockDate() {
        return blockDate;
    }

    public String getBlockedUserEmail() {
        return blockedUserEmail;
    }

    public void setBlockedByAccount(AccountEntity blockedByAccount) {
        this.blockedByAccount = blockedByAccount;
    }

    public void setBlockedAccounts(AccountEntity blockedAccount) {
        this.blockedAccounts = blockedAccount;
    }

    public void setBlockedUserEmail(String blockedUserEmail) {
        this.blockedUserEmail = blockedUserEmail;
    }

    public void setBlockDate(Date blockDate) {
        this.blockDate = blockDate;
    }

    @Override
    public String toString() {
        return "BlockedAccountsEntity{" +
            "id=" + id +
            ", blockedByAccount=" + blockedByAccount +
            ", blockedAccounts=" + blockedAccounts +
            ", blockedUserEmail='" + blockedUserEmail + '\'' +
            ", blockDate=" + blockDate +
            '}';
    }
}
