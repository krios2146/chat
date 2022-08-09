import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  private options = {headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')};

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string): Observable<any> {
    const payload = new HttpParams()
    .set("username", username)
    .set("password", password);

    return this.http.post(`${environment.apiBaseUrl}/login`, payload, this.options);
  }
}