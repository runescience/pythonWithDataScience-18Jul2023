
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Planet } from '../models/planet.interface';

@Injectable({
    providedIn: 'root'
})
export class PlanetService {
    private apiUrl = 'http://localhost:3000/planet';

    constructor(private http: HttpClient) { }

    getAllPlanets(): Observable<Planet[]> {
        return this.http.get<Planet[]>(this.apiUrl);
    }

    getPlanetById(planet_id: string): Observable<Planet> {
        return this.http.get<Planet>(`${this.apiUrl}/${planet_id}`);
    }

    createPlanet(planet: Partial<Planet>): Observable<Planet> {
        return this.http.post<Planet>(this.apiUrl, planet);
    }

    updatePlanet(planet_id: string, planet: Partial<Planet>): Observable<Planet> {
        return this.http.put<Planet>(`${this.apiUrl}/${planet_id}`, planet);
    }

    deletePlanet(planet_id: string): Observable<{ message: string }> {
        return this.http.delete<{ message: string }>(`${this.apiUrl}/${planet_id}`);
    }
}
