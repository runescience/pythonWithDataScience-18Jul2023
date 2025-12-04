
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cache } from '../models/cache.interface';

@Injectable({
  providedIn: 'root'
})
export class CacheService {
  private apiUrl = 'http://localhost:3000/cache';

  constructor(private http: HttpClient) {}

  getAllCacheItems(): Observable<Cache[]> {
    return this.http.get<Cache[]>(this.apiUrl);
  }

  getCacheItemById(cache_id: string): Observable<Cache> {
    return this.http.get<Cache>(`${this.apiUrl}/${cache_id}`);
  }

  createCacheItem(cacheItem: Partial<Cache>): Observable<Cache> {
    return this.http.post<Cache>(this.apiUrl, cacheItem);
  }

  updateCacheItem(cache_id: string, cacheItem: Partial<Cache>): Observable<Cache> {
    return this.http.put<Cache>(`${this.apiUrl}/${cache_id}`, cacheItem);
  }

  deleteCacheItem(cache_id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${cache_id}`);
  }
}
