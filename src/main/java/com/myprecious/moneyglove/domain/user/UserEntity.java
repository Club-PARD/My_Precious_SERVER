package com.myprecious.moneyglove.domain.user;

import com.myprecious.moneyglove.common.BaseEntity;
import com.myprecious.moneyglove.domain.board.BoardEntity;
import com.myprecious.moneyglove.domain.debt.DebtEntity;
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
public class UserEntity extends BaseEntity {

    @Id
    private String uid; //파이어베이스에서 받는 id 값

    private String name;
    private String birth;
    private String phoneNum;
    private String gmailId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardEntity> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DebtEntity> debts = new ArrayList<>();

    @Builder
    public UserEntity(String name, String birth, String phoneNum, String gmailId,
                      String uid) {
        this.name = name;
        this.birth = birth;
        this.phoneNum = phoneNum;
        this.gmailId = gmailId;
        this.uid = uid;
    }

    public void update(UserUpdateRequest request) {
        if (name != null) {
            this.name = request.getName();
        }

        if (birth != null) {
            this.birth = request.getBirth();
        }

        if (phoneNum != null) {
            this.phoneNum = request.getPhoneNum();
        }
    }

}

