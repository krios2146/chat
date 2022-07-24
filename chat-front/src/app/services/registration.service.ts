import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { User } from './../user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient, private router: Router) { }

  registerUser(user: User): void {
    this.http.post<User>(`${environment.apiBaseUrl}/registration`, user);
    this.router.navigateByUrl('');
  }
}
