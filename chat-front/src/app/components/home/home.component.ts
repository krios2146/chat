import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RxStompService } from 'src/app/services/rx-stomp.service';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn: boolean = false;
  loggedInUser!: string;

  constructor(private router: Router, private authService: AuthenticationService, private rxStompService: RxStompService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isUserLoggedIn();
    this.loggedInUser = this.authService.getLoggedInUser();

    if (!this.isLoggedIn) {
      this.router.navigateByUrl('login');
    }
  }

  logout(): void {
    this.authService.logout();
  }

  onSendMessage(message: string) {

  }

}
