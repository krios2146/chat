import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  SESSINO_KEY = 'auth_user';

  username!: string;
  password!: string;

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string): Observable<void> {
    return this.http.get('http://localhost:8080/login', {
      headers: { authorization: this.createBasicAuthToken(username, password) }
    })
    .pipe(map((res) => {
      this.username = username;
      this.password = password;
      this.registerInSession(username);
    }));
  }

  createBasicAuthToken(username: string, password: string): string {
    return 'Basic ' + window.btoa(username + ':' + password);
  }

  registerInSession(username: string): void {
    sessionStorage.setItem(this.SESSINO_KEY, username);
  }

  logout(): void {
    sessionStorage.removeItem(this.SESSINO_KEY);
    this.username = null!;
    this.password = null!;
  }

  isUserLoggedIn(): boolean {
    let user = sessionStorage.getItem(this.SESSINO_KEY);
    if (user === null) {
      return false;
    }
    return true;
  }

  getLoggedInUser(): string {
    let user = sessionStorage.getItem(this.SESSINO_KEY);
    if (user === null) {
      return '';
    }
    return user;
  }
}
