
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hazard } from '../models/hazard.interface';

@Injectable({
    providedIn: 'root'
})
export class HazardService {
    private apiUrl = 'http://localhost:3000/hazard';

    constructor(private http: HttpClient) { }

    getAllHazards(): Observable<Hazard[]> {
        return this.http.get<Hazard[]>(this.apiUrl);
    }

    getHazardById(hazard_id: string): Observable<Hazard> {
        return this.http.get<Hazard>(`${this.apiUrl}/${hazard_id}`);
    }

    createHazard(hazard: Partial<Hazard>): Observable<Hazard> {
        return this.http.post<Hazard>(this.apiUrl, hazard);
    }

    updateHazard(hazard_id: string, hazard: Partial<Hazard>): Observable<Hazard> {
        return this.http.put<Hazard>(`${this.apiUrl}/${hazard_id}`, hazard);
    }

    deleteHazard(hazard_id: string): Observable<{ message: string }> {
        return this.http.delete<{ message: string }>(`${this.apiUrl}/${hazard_id}`);
    }
}
