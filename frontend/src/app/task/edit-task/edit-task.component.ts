import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Task } from '../shared/task.model';
import { Subscription } from 'rxjs';
import { TaskService } from '../shared/task.service';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent implements OnInit, OnDestroy {
  task: Task = { description: '', status: false };
  private subscription: Subscription;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private taskService: TaskService) { }

  ngOnInit() {
    this.subscription = this.route.params.subscribe((params) => {
      if (params['id']) {
        this.load(params['id']);
      }
    });
  }

  load(id) {
    this.taskService.getTask(id).subscribe((task) => {
      this.task = task;
    });
  }

  onTaskSaved(event) {
    if (event) {
      this.router.navigate(['', 'task']);
    }
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
