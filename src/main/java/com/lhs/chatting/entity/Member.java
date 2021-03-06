package com.lhs.chatting.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@Builder
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Setter
    @Column(name = "room_alias", length = 45, nullable = false)
    private String roomAlias;

    @Setter
    @Column(name = "setting_meta", length = 45, nullable = false)
    private String settingMeta;

    @Column(name = "joined_time")
    private Timestamp joinedTime;

    @Setter
    @Column(name = "last_entrance_time")
    private Timestamp lastEntranceTime;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Room.class)
    @JoinColumn(name = "room_id")
    private Room room;

    public static Member of(Long userId, Long roomId, String roomAlias) {
        return Member.builder().roomAlias(roomAlias).settingMeta("NORMAL")
                .joinedTime(new Timestamp(System.currentTimeMillis()))
                .lastEntranceTime(new Timestamp(System.currentTimeMillis())).user(User.builder().id(userId).build())
                .room(Room.builder().id(roomId).build()).build();
    }
}