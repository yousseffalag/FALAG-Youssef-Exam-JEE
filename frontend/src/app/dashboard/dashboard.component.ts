import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  clientsCount = 0;
  contratsCount = 0;
  paiementsCount = 0;
  loading = true;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    forkJoin({
      clients: this.apiService.getClients(),
      contrats: this.apiService.getContrats(),
      paiements: this.apiService.getPaiements()
    }).subscribe({
      next: data => {
        this.clientsCount = data.clients.length;
        this.contratsCount = data.contrats.length;
        this.paiementsCount = data.paiements.length;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }
}
