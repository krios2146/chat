import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private options = {headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')};

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string): void {
    const params = new HttpParams()
    .set("username", username)
    .set("password", password);

    this.http.post(`${environment.apiBaseUrl}/login`, params, this.options).subscribe();
  }

  logout(): void { }

  isUserLoggedIn(): boolean { return false; }
}
