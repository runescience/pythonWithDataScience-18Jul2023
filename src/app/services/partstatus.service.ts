
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PartStatus } from '../models/partstatus.interface';

@Injectable({
  providedIn: 'root'
})
export class PartStatusService {
  private apiUrl = 'http://localhost:3000/partstatus';

  constructor(private http: HttpClient) { }

  getPartStatuses(): Observable<PartStatus[]> {
    return this.http.get<PartStatus[]>(this.apiUrl);
  }

  getPartStatus(id: string): Observable<PartStatus> {
    return this.http.get<PartStatus>(`${this.apiUrl}/${id}`);
  }

  createPartStatus(partStatus: PartStatus): Observable<PartStatus> {
    return this.http.post<PartStatus>(this.apiUrl, partStatus);
  }

  updatePartStatus(id: string, partStatus: PartStatus): Observable<PartStatus> {
    return this.http.put<PartStatus>(`${this.apiUrl}/${id}`, partStatus);
  }

  deletePartStatus(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
