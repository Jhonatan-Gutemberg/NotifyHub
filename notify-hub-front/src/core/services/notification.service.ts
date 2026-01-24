import { HttpClient } from "@angular/common/http";
import { UserNotification } from "../models/userNotification";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environments.development";

@Injectable({providedIn: 'root'})
export class NotificationService{
    private readonly API = environment.apiiNotifications;

    constructor(private http: HttpClient){}

    send(data: any): Observable<String>{
        return this.http.post(this.API, data,{responseType: 'text'});
    }
}