import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn: boolean = false;
  loggedInUser: string = '';


  constructor(private route: ActivatedRoute, private router: Router, 
    private http: HttpClient, private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isUserLoggedIn();
    this.loggedInUser = this.authService.getLoggedInUser();

    if (!this.isLoggedIn) {
      this.router.navigateByUrl('login');
    }
  }

}
