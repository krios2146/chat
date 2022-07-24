package com.krios.chat.message;

import com.krios.chat.appuser.AppUser;
import com.krios.chat.chatroom.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @SequenceGenerator(name = "message_sequence", sequenceName = "message_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_sequence")
    private Long id;

    @ManyToMany
    @JoinTable(name = "message_chat_room",
            joinColumns = @JoinColumn(name = "chat_room_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "message_id", referencedColumnName = "id")
    )
    private List<ChatRoom> chatId;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser senderId;

    private String text;
    private Date timestamp;
    private boolean isChanged;

    public Message(String text) {
        this.text = text;
    }
}