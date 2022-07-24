package com.krios.chat.chatroom;

import com.krios.chat.appuser.AppUser;
import com.krios.chat.message.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {
    @SequenceGenerator(name = "chat_room_sequence", sequenceName = "chat_room_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_room_sequence")
    private Long id;

    @OneToMany
    @JoinColumn(name = "app_user_id")
    private List<AppUser> chatRoomMembersId;

    @ManyToMany(mappedBy = "chatId")
    private List<Message> chatRoomMessagesId;
}
