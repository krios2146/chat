import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username!: string;
  password!: string;
  isLoggedIn: boolean = false;
  error!: string;

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isUserLoggedIn();

    if (this.isLoggedIn) {
      this.router.navigateByUrl('');
    }
  }

  login(): void {

    if (this.username === '' && this.username === null && this.password === '' && this.password === null) {
      this.error = 'Invalid credentials';
      return;
    }

    this.authService.authenticate(this.username, this.password).subscribe((result) => {
      console.log(result);
      this.router.navigateByUrl('');
    }), () => {
      this.error = 'Invalid credentials';
    }
  }
  
}
