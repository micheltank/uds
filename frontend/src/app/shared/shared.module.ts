import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import {
  MatButtonModule,
  MatCardModule,
  MatIconModule,
  MatMenuModule,
  MatInputModule,
  MatCheckboxModule,
  MatToolbarModule,
  MatSidenavModule,
} from '@angular/material';

const ANGULAR_MODULES: any[] = [FormsModule, ReactiveFormsModule];

const MATERIAL_MODULES: any[] = [
  MatButtonModule,
  MatCardModule,
  MatIconModule,
  MatMenuModule,
  MatInputModule,
  MatCheckboxModule,
  MatToolbarModule,
  MatSidenavModule,
];

const FLEX_LAYOUT_MODULES: any[] = [FlexLayoutModule];

@NgModule({
  imports: [
    CommonModule,
    ANGULAR_MODULES,
    MATERIAL_MODULES,
    FLEX_LAYOUT_MODULES
  ],
  exports: [
    CommonModule,
    ANGULAR_MODULES,
    MATERIAL_MODULES,
    FLEX_LAYOUT_MODULES
  ]
})
export class SharedModule {}
