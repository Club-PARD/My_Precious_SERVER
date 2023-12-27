package com.myprecious.moneyglove.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Float borrowMoney;
    @Column(nullable = false)
    private Integer payYear;
    @Column(nullable = false)
    private Integer payMonth;
    @Column(nullable = false)
    private Integer payDay;
    @Column(nullable = false)
    private String situation;
    @Column(nullable = false)
    private String payWay;
    @Column(nullable = false)
    private String bank;
    @Column(nullable = false)
    private String bankAccount;

    @Builder
    public BoardEntity(String title, Float borrowMoney, Integer payYear, Integer payMonth,
                       Integer payDay, String situation, String payWay, String bank, String bankAccount ) {
        this.title = title;
        this. borrowMoney = borrowMoney;
        this.payYear = payYear;
        this.payMonth = payMonth;
        this.payDay = payDay;
        this.situation = situation;
        this.payWay = payWay;
        this.bank = bank;
        this.bankAccount = bankAccount;
    }

}