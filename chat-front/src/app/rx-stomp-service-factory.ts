import { myRxStompConfig } from "./rx-stomp.config";
import { RxStompService } from "./_services/rx-stomp.service";

export function rxStompServiceFactory() {
    const rxStomp = new RxStompService();
    rxStomp.configure(myRxStompConfig);
    rxStomp.activate();
    return rxStomp;
}