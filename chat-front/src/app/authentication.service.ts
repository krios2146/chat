import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  SESSION_KEY: string = 'auth_user';

  username!: string;
  password!: string;

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string): Observable<void> {
    return this.http.get(`${environment.apiBaseUrl}/login`, {
      headers: { authorization: this.createBasicAuthToken(username, password) }
    })
      .pipe(map(() => {
        this.username = username;
        this.password = password;
        this.registerInSession(username);
      }));
  }

  createBasicAuthToken(username: string, password: string): string {
    return 'Basic ' + window.btoa(username + ':' + password);
  }

  registerInSession(username: string): void {
    sessionStorage.setItem(this.SESSION_KEY, username);
  }

  logout(): void {
    sessionStorage.removeItem(this.SESSION_KEY);
    this.username = null!;
    this.password = null!;
  }

  isUserLoggedIn(): boolean {
    let user = sessionStorage.getItem(this.SESSION_KEY);
    if (user === null) {
      return false;
    }
    return true;
  }

  getLoggedInUser(): string {
    let user = sessionStorage.getItem(this.SESSION_KEY);
    if (user === null) {
      return '';
    }
    return user;
  }
}
