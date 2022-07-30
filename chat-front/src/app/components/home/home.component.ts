import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RxStompService } from 'src/app/services/rx-stomp.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { MessageService } from 'src/app/services/message.service';
import { Message } from 'src/app/message';

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

  constructor(private router: Router, 
    private authService: AuthenticationService, 
    private rxStompService: RxStompService, 
    private messageService: MessageService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isUserLoggedIn();

    if (!this.isLoggedIn) {
      this.router.navigateByUrl('login');
    }

    this.messageService.getAllMessages().subscribe((m) => this.messages = m);
  }

  logout(): void {
    this.authService.logout();
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
