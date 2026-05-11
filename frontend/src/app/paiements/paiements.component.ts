import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { forkJoin, of } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { Paiement } from '../models/api.models';
import { ApiService } from '../services/api.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-paiements',
  templateUrl: './paiements.component.html'
})
export class PaiementsComponent implements OnInit {
  paiements: Paiement[] = [];
  paiementForm: FormGroup;
  loading = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    public authService: AuthService
  ) {
    this.paiementForm = this.fb.group({
      contratId: [null, Validators.required],
      date: ['', Validators.required],
      montant: [null, [Validators.required, Validators.min(1)]],
      type: ['MENSUALITE', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadPaiements();
  }

  loadPaiements(): void {
    this.loading = true;
    this.errorMessage = '';

    if (this.authService.isClient()) {
      const clientId = this.authService.getClientId();
      if (!clientId) {
        this.paiements = [];
        this.loading = false;
        return;
      }

      this.apiService.getContratsByClient(clientId).pipe(
        switchMap(contrats => {
          if (!contrats.length) {
            return of([] as Paiement[]);
          }
          const requests = contrats
            .filter(contrat => !!contrat.id)
            .map(contrat => this.apiService.getPaiementsByContrat(contrat.id!).pipe(catchError(() => of([] as Paiement[]))));
          return forkJoin(requests);
        })
      ).subscribe({
        next: paymentsByContract => {
          this.paiements = paymentsByContract.flat();
          this.loading = false;
        },
        error: () => {
          this.errorMessage = 'Impossible de charger les paiements.';
          this.loading = false;
        }
      });
      return;
    }

    this.apiService.getPaiements().subscribe({
      next: paiements => {
        this.paiements = paiements;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Impossible de charger les paiements.';
        this.loading = false;
      }
    });
  }

  createPaiement(): void {
    if (this.paiementForm.invalid) return;

    this.apiService.createPaiement(this.paiementForm.value).subscribe({
      next: () => {
        this.paiementForm.reset({ type: 'MENSUALITE' });
        this.loadPaiements();
      },
      error: () => this.errorMessage = 'Création du paiement impossible.'
    });
  }

  get pageTitle(): string {
    return this.authService.isClient() ? 'Mes paiements' : 'Paiements';
  }
}
