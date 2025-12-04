
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ship } from '../models/ship.interface';

@Injectable({
  providedIn: 'root'
})
export class ShipService {
  private apiUrl = 'http://localhost:3000/ship';

  constructor(private http: HttpClient) { }

  getAllShips(): Observable<Ship[]> {
    return this.http.get<Ship[]>(this.apiUrl);
  }

  getShipById(ship_id: string): Observable<Ship> {
    return this.http.get<Ship>(`${this.apiUrl}/${ship_id}`);
  }

  createShip(ship: Ship): Observable<Ship> {
    return this.http.post<Ship>(this.apiUrl, ship);
  }

  updateShip(ship_id: string, ship: Ship): Observable<Ship> {
    return this.http.put<Ship>(`${this.apiUrl}/${ship_id}`, ship);
  }

  deleteShip(ship_id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${ship_id}`);
  }
}
