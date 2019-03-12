import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Task } from '../task.model';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent implements OnInit, OnDestroy {

  @Input() task: Task = { description: '', status: false };
  @Output() save: EventEmitter<any> = new EventEmitter<any>();
  sub: Subscription;

  constructor(private taskService: TaskService) { }

  submit() {
    const _body = { description: this.task.description, status: this.task.status };

    if (this.task.id) {
      this.taskService
        .updateTask(this.task.id, _body)
        .subscribe((data) => {
          this.save.emit(true);
        },
          (error) => {
            this.save.emit(false);
          }
        );
    } else {
      this.taskService
        .saveTask(_body)
        .subscribe((data) => {
          this.save.emit(true);
        },
          (error) => {
            this.save.emit(false);
          }
        );
    }

  }

  ngOnInit() {
  }

  ngOnDestroy() {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }
}
