import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { environment } from 'environment/environment';
import { Observable } from 'rxjs';
import { WO } from '../models/wo';

@Injectable({
  providedIn: 'root'
})
export class WoService {
  baseUrl: String = environment.baseUrl;

  constructor(private http: HttpClient, private snack: MatSnackBar) {}

  //Get method to backend url
  findAll(): Observable<WO[]> {
    const url = this.baseUrl + "/wo";
    return this.http.get<WO[]>(url);
  }

  findById(id : any):Observable<WO>{
    const url = `${this.baseUrl}/wo/${id}`;
    return this.http.get<WO>(url);

  } 

  //Post method to backend url
  create(wo: WO): Observable<WO> {
    const url = this.baseUrl + "/wo";
    return this.http.post<WO>(url, wo);
  }

  update(wo: WO): Observable<WO> {
    const url = `${this.baseUrl}/wo/${wo.id}`;
    return this.http.put<WO>(url, wo);
  }

  delete(id : any): Observable<void>{
    const url = `${this.baseUrl}/wo/${id}`;
    return this.http.delete<void>(url);
  }

  message(msg: String): void {
    this.snack.open(`${msg}`, "OK", {
      horizontalPosition: "end",
      verticalPosition: "top",
      duration: 4000,
    });
  }
}
