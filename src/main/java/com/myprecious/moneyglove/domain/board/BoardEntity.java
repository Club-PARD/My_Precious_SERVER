package com.myprecious.moneyglove.board;

import com.myprecious.moneyglove.entity.BaseEntity;
import com.myprecious.moneyglove.user.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.Period;

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
    private String payDate;

    @Column(nullable = false)
    private String situation;

    @Column(nullable = false)
    private String payWay;

    @Column(nullable = false)
    private String bank;

    @Column(nullable = false)
    private String bankAccount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.getDefault();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public BoardEntity(String title, Float borrowMoney, String payDate, String situation, String payWay, String bank, String bankAccount ) {
        this.title = title;
        this.borrowMoney = borrowMoney;
        this.payDate = payDate;
        this.situation = situation;
        this.payWay = payWay;
        this.bank = bank;
        this.bankAccount = bankAccount;

    }

    public enum Status {
        RECEIVED("Received"),
        NOT_RECEIVED("Not Received");

        private final String label;

        // Enum 생성자에 기본값을 할당
        Status(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        // 기본값을 반환하는 메서드 추가
        public static Status getDefault() {
            return NOT_RECEIVED;
        }
    }


}
