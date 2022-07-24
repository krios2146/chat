package com.krios.chat;

import com.krios.chat.appuser.AppUser;
import com.krios.chat.appuser.AppUserService;
import com.krios.chat.chatroom.ChatRoom;
import com.krios.chat.chatroom.ChatRoomService;
import com.krios.chat.message.Message;
import com.krios.chat.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    public final MessageService messageService;
    public final ChatRoomService chatRoomService;
    public final AppUserService appUserService;

    @RequestMapping(path = "/messages")
    public ResponseEntity<List<Message>> getMessages() {
        return new ResponseEntity<>(messageService.getMessages(), HttpStatus.OK);
    }

    @RequestMapping(path = "/chatrooms")
    public ResponseEntity<List<ChatRoom>> getChatRooms() {
        return new ResponseEntity<>(chatRoomService.getChatRooms(), HttpStatus.OK);
    }

    @RequestMapping(path = "/users")
    public ResponseEntity<List<AppUser>> getUsers() {
        return new ResponseEntity<>(appUserService.getUsers(), HttpStatus.OK);
    }
}
