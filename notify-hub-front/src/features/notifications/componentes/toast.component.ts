import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastService } from '../../../core/services/toast.service';

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div *ngIf="toast$ | async as toast" class="toast-box" [ngClass]="toast.type">
      {{ toast.message }}
    </div>
  `,
  styleUrls: ['./toast.component.css']
})
export class ToastComponent {
  private toastService = inject(ToastService);
  
  toast$ = this.toastService.toastState$;
}