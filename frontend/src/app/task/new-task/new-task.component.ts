import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from '../shared/task.model';

@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent {
  task: Task = { description: '', status: false};

  constructor( private router: Router) { }

  onTaskSaved(event) {
    if (event) {
      this.router.navigate(['', 'task']);
    }
  }

}
