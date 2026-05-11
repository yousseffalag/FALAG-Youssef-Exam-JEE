import { Component, OnInit } from '@angular/core';
import { Contrat } from '../models/api.models';
import { ApiService } from '../services/api.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-contrats',
  templateUrl: './contrats.component.html'
})
export class ContratsComponent implements OnInit {
  contrats: Contrat[] = [];
  selectedContrat: Contrat | null = null;
  loading = false;
  errorMessage = '';

  constructor(private apiService: ApiService, public authService: AuthService) {}

  ngOnInit(): void {
    this.loadContrats();
  }

  loadContrats(): void {
    this.loading = true;
    const clientId = this.authService.getClientId();

    const request = clientId
      ? this.apiService.getContratsByClient(clientId)
      : this.apiService.getContrats();

    request.subscribe({
      next: contrats => {
        this.contrats = contrats;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Impossible de charger les contrats.';
        this.loading = false;
      }
    });
  }

  selectContrat(contrat: Contrat): void {
    this.selectedContrat = contrat;
  }

  get pageTitle(): string {
    return this.authService.isClient() ? 'Mes contrats' : 'Contrats assurance';
  }
}
