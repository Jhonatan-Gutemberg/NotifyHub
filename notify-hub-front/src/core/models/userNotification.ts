export interface UserNotification {
    name: string;
    email: string;
    type: 'EMAIL' | 'SMS' | 'PUSH';
    priority: 'LOW' | 'MEDIUM' | 'HIGH';
}