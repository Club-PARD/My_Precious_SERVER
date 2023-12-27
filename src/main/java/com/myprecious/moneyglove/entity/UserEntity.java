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
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false, unique = true)
    private String gmailId;

    @Column(nullable = false, unique = true)
    private String userId;

    @Builder
    public UserEntity(String name, String birth, String phoneNum, String gmailId,
                      String userId){
        this.name = name;
        this.birth = birth;
        this.phoneNum = phoneNum;
        this.gmailId = gmailId;
        this.userId = userId;
    }

//    public void update(String name, Integer num, String birth, String phoneNum, String gmailId,
//                       String userId) {
//        if (name != null) {
//            this.name = name;
//        }
//
//        if (birth != null) {
//            this.birth = birth;
//        }
//
//        if (phoneNum != null) {
//            this.phoneNum = phoneNum;
//        }
//
//        if (gmailId != null) {
//            this.gmailId = gmailId;
//        }
//
//    }
}


//    //엔티티 연결
//    // 하나의 동아리는 여러개의 시트를 잡을 수 있음
//    @OneToMany(mappedBy = "club", cascade = CascadeType.PERSIST)
//    private List<Sheet> checkedSheet = new ArrayList<>();
//
//    // 동아리에 연결된 시트를 삭제
//    public void delete() {
//        for (Sheet sheet : checkedSheet) {
//            sheet.setClub(null); // 동아리와의 연관 관계 제거
//        }
//        checkedSheet.clear(); // 리스트 비우기
//    }

