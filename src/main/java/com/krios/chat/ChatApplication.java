package com.krios.chat;

import com.krios.chat.appuser.AppUser;
import com.krios.chat.appuser.AppUserRole;
import com.krios.chat.appuser.AppUserService;
import com.krios.chat.chatroom.ChatRoom;
import com.krios.chat.chatroom.ChatRoomService;
import com.krios.chat.message.Message;
import com.krios.chat.message.MessageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	// Initial user
	@Bean
	CommandLineRunner runner(AppUserService appUserService, MessageService messageService, ChatRoomService chatRoomService) {
		return args -> {
			appUserService.registerUser(
					new AppUser(
						"username",
						"email",
						"password",
						"John",
						"Smith",
						AppUserRole.USER
					)
			);
			messageService.saveMessage(new Message("Test Message"));
			messageService.saveMessage(new Message("Test Message 2"));
			chatRoomService.saveChatRoom(new ChatRoom());
		};
	}
}
