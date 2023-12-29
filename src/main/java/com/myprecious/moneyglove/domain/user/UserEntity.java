package com.myprecious.moneyglove.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myprecious.moneyglove.BaseEntity;
import com.myprecious.moneyglove.domain.board.BoardEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId; //파이어베이스에서 받는 id 값
    private String name;
    private String birth;
    private String phoneNum;
    private String gmailId;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<BoardEntity> boards = new ArrayList<>();

    @Builder
    public UserEntity(String name, String birth, String phoneNum, String gmailId,
                      String userId) {
        this.name = name;
        this.birth = birth;
        this.phoneNum = phoneNum;
        this.gmailId = gmailId;
        this.userId = userId;
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

