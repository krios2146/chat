import { RxStompConfig } from "@stomp/rx-stomp";

export const myRxStompConfig: RxStompConfig = {
    brokerURL: 'ws://localhost:8080/ws',
    connectHeaders: {},
    heartbeatIncoming: 0,
    heartbeatOutgoing: 20000,
    reconnectDelay: 2000,
    debug: (msg: string): void => {
        console.log(new Date(), msg);
    }
}