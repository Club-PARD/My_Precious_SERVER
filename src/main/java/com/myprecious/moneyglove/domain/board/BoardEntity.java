package com.myprecious.moneyglove.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myprecious.moneyglove.common.BaseEntity;
import com.myprecious.moneyglove.domain.debt.DebtEntity;
import com.myprecious.moneyglove.domain.user.UserEntity;
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
@Table(name = "board_entity")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String borrowMoney;
    private String payDate;
    private String situation;
    private String payWay;
    private String bank;
    private String bankAccount;
    private Integer dDay;
    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uid", referencedColumnName = "uid")
    @JsonIgnore
    private UserEntity user;


    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DebtEntity> debts = new ArrayList<>();

    @Builder
    public BoardEntity(String title, String borrowMoney, String payDate, String situation,
                       String payWay, String bank, String bankAccount, Integer dDay, UserEntity user) {
        this.title = title;
        this.borrowMoney = borrowMoney;
        this.payDate = payDate;
        this.situation = situation;
        this.payWay = payWay;
        this.bank = bank;
        this.bankAccount = bankAccount;
        this.boardStatus = BoardStatus.NOTYET;
        this.dDay = dDay;
        this.user = user;
    }

    public enum BoardStatus {
        PAIDALL("All"),
        NOTYET("NOT");


        private final String label;

        BoardStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
