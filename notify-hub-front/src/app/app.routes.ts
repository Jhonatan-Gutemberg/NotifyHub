import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'notifications',
    loadChildren: () => import('../features/notifications/notifications.routes')
      .then(m => m.NOTIFICATION_ROUTES)
  },
  {
    path: '', 
    redirectTo: 'notifications',
    pathMatch: 'full'
  }
];