import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from '../shared/shared.module';
import { TaskRoutingModule } from './task-routing.module';
import { TaskListComponent } from './task-list/task-list.component';
import { NewTaskComponent } from './new-task/new-task.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
import { TaskFormComponent } from './shared/task-form/task-form.component';
import { TaskService } from './shared/task.service';

@NgModule({
  imports: [
    SharedModule,
    HttpClientModule,
    TaskRoutingModule
  ],
  declarations: [
    TaskListComponent,
    NewTaskComponent,
    EditTaskComponent,
    TaskFormComponent
  ],
  exports: [
    TaskListComponent,
    NewTaskComponent,
    EditTaskComponent,
    TaskFormComponent
  ],
  providers: [
    TaskService
  ]
})
export class TaskModule { }
