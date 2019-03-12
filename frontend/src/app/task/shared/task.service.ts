import { Injectable, Inject } from '@angular/core';
import { Task } from './task.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { HttpParams } from '@angular/common/http';

const apiUrl = environment.baseApiUrl + '/tasks';

@Injectable()
export class TaskService {

  constructor(private http: HttpClient) { }

  getTasks(): Observable<any> {
    return this.http.get(`${apiUrl}`);
  }

  getTask(id: string): Observable<any> {
    return this.http.get(`${apiUrl}/${id}`);
  }

  saveTask(data: Task) {
    console.log('saving task:' + data);
    return this.http.post(`${apiUrl}`, data);
  }

  updateTask(id: string, data: Task) {
    console.log('updating task:' + data);
    return this.http.put(`${apiUrl}/${id}`, data);
  }

  deleteTask(id: string) {
    console.log('delete task by id:' + id);
    return this.http.delete(`${apiUrl}/${id}`);
  }

}
