import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private isLoggedIn: boolean = false;

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isUserLoggedIn();

    if (this.isLoggedIn) {
      this.router.navigateByUrl('');
    }
  }

  login(username: string, password: string): void {

    if (username === '' || username === undefined || password === '' || password === undefined) {
      console.log('Invalid credentials');
    }

    this.authService.authenticate(username, password);

    console.log("Request sended");
  }
  
}
