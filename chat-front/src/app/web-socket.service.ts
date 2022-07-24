import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  constructor() { }

  initializeConnection() {
    const stompClient = Stomp.over(new SockJS(`${environment.apiBaseUrl}/ws`));
    stompClient.connect({}, () => {
      console.log('connected');
    });
  }
}
