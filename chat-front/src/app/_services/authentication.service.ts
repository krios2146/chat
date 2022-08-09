import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private formData: any = new FormData();

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string): void {
    this.formData.append("username", username);
    this.formData.append("password", password);
    this.http.post(`${environment.apiBaseUrl}/login`, this.formData, {observe: "response", responseType: "text"}).subscribe((response: HttpResponse<any>) => {
      console.log(response.headers.keys());
    });
  }

  logout(): void { }

  isUserLoggedIn(): boolean { return false; }
}
