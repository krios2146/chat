export interface Message {
    id: number;
    chatId: number[];
    senderId: number[];
    text: string;
    timestamp: Date;
    ischanged: boolean;
}