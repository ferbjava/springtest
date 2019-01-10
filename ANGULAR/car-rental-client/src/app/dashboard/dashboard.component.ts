import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(
    private router: Router,
  ) { }

  ngOnInit() {
  }

  goToDepartments(): void {
    this.router.navigate(['/departments']);
  }

  goToCars(): void {
    this.router.navigate(['/cars']);
  }

  goToClients(): void {
    this.router.navigate(['/clients']);
  }
}
