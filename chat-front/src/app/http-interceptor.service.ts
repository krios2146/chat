import { HttpEvent, HttpHandler, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService {

  constructor(private authService: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (!this.authService.isUserLoggedIn() && request.url.indexOf('basicauth') !== -1) {
      return next.handle(request);
    }

    const headerReq = request.clone({
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Basic ${window.btoa(this.authService.username + ":" + this.authService.password)}`
        })
    });

    return next.handle(headerReq);
  }
}
