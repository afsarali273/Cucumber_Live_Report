import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { TestSummaryComponent } from './components/test-summary/test-summary.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'test-summary', component: TestSummaryComponent },
  { path: 'about', component: AboutComponent },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
