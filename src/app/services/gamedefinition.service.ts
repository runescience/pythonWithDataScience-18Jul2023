
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GameDefinition } from '../models/gamedefinition.interface';

@Injectable({
  providedIn: 'root'
})
export class GameDefinitionService {
  private apiUrl = 'http://0.0.0.0:3000/gamedefinition';

  constructor(private http: HttpClient) {}

  getAllGameDefinitions(): Observable<GameDefinition[]> {
    return this.http.get<GameDefinition[]>(this.apiUrl);
  }

  getGameDefinitionById(id: string): Observable<GameDefinition> {
    return this.http.get<GameDefinition>(`${this.apiUrl}/${id}`);
  }

  createGameDefinition(gameDefinition: Partial<GameDefinition>): Observable<GameDefinition> {
    return this.http.post<GameDefinition>(this.apiUrl, gameDefinition);
  }

  updateGameDefinition(id: string, gameDefinition: Partial<GameDefinition>): Observable<GameDefinition> {
    return this.http.put<GameDefinition>(`${this.apiUrl}/${id}`, gameDefinition);
  }

  deleteGameDefinition(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
