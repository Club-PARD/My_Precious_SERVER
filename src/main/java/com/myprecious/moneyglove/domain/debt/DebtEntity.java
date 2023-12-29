package com.myprecious.moneyglove.domain.debt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myprecious.moneyglove.BaseEntity;
import com.myprecious.moneyglove.domain.board.BoardEntity;
import com.myprecious.moneyglove.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DebtEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lendMoney;
    private String message;
    private String bank;
    private String bankAccount;

    // 빚 상태
    @Enumerated(EnumType.STRING)
    private DebtStatus debtStatus;

    // 갚은 상태
    @Enumerated(EnumType.STRING)
    private RepaymentStatus repaymentStatus;

    @Builder
    public DebtEntity(String lendMoney, String message, String bank, String bankAccount,
                      UserEntity user, BoardEntity board) {
        this.lendMoney = lendMoney;
        this.message = message;
        this.bankAccount = bankAccount;
        this.bank = bank;
        this.debtStatus = DebtStatus.PENDING;
        this.repaymentStatus = RepaymentStatus.NOT_CONFIRMED;
        this.user = user;
        this.board = board;
    }

    public enum DebtStatus {
        PENDING("Pending"), // 아직 갚지 않은 상태
        PAID("Paid"); // 갚은 상태

        private final String label;

        DebtStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum RepaymentStatus {
        NOT_CONFIRMED("Not Confirmed"), // 아직 확인되지 않은 상태
        CONFIRMED("Confirmed"); // 확인된 상태

        private final String label;

        RepaymentStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private BoardEntity board;
}
