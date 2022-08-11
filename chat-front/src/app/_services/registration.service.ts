import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../_models/user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private options = {headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')};

  constructor(private http: HttpClient) { }

  registerUser(user: User): Observable<any> {
    const payload = new HttpParams()
    .set("username", user.username)
    .set("password", user.password)
    .set("firstName", user.firstName)
    .set("lastName", user.lastName)
    .set("email", user.email);
    return this.http.post<User>(`${environment.apiBaseUrl}/registration`, payload, this.options);
  }
}
