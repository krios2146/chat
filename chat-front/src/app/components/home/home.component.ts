import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WebSocketService } from '../../web-socket.service';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn: boolean = false;
  loggedInUser!: string;

  constructor(private router: Router, private authService: AuthenticationService, private wsService: WebSocketService) { }

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

  connect() {
    this.wsService.initializeConnection();
  }

}
