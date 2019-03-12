import { Component, OnInit, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { Task } from '../shared/task.model';
import { Subscription } from 'rxjs';
import { TaskService } from '../shared/task.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit, OnDestroy {

  tasks: Task[];
  sub: Subscription;

  constructor(private router: Router,
    private taskService: TaskService) {
  }

  search() {
    this.sub = this.taskService.getTasks()
      .subscribe(
        data => this.tasks = data,
        err => console.log(err)
      );
  }

  addTask() {
    this.router.navigate(['', 'task', 'new']);
  }

  deleteTask(id) {
    this.taskService.deleteTask(id).subscribe((response) => {
      const newTasks = this.tasks.filter((task) => {
        return task.id !== id;
      });
      this.tasks = newTasks;
    });
  }

  ngOnInit() {
    this.search();
  }

  ngOnDestroy() {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }

}
