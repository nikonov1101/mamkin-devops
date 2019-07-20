import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

export interface IListItem {
  title: string;
  id: number;
}

@Injectable({
  providedIn: 'root'
})
export class HelloService {

  constructor(private http: HttpClient) {
  }

  getList(): Observable<IListItem[]> {
    return this.http.get<IListItem[]>('/api/v1/list')
  }
}
