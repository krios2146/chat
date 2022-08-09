import { Component, OnInit } from '@angular/core';
import { RxStompService } from 'src/app/_services/rx-stomp.service';
import { MessageService } from 'src/app/_services/message.service';
import { Message } from 'src/app/_models/message';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn: boolean = false;
  loggedInUser!: string;
  messages: Message[] = [];
  subMsg!: string;

  constructor(private rxStompService: RxStompService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.messageService.getAllMessages().subscribe((m) => this.messages = m);
  }

  onSendMessage(messageText: string) {
    this.rxStompService.publish({
      destination: "/app/chat/sendMessage",
      body: messageText
    });
  }

  getSub() {
    this.rxStompService.watch("/topic/public").subscribe((msg) => {
      this.subMsg = msg.body;
      console.log(msg.body);
    });

  }
}
