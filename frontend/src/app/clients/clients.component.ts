import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Client } from '../models/api.models';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html'
})
export class ClientsComponent implements OnInit {
  clients: Client[] = [];
  clientForm: FormGroup;
  loading = false;
  errorMessage = '';

  constructor(private fb: FormBuilder, private apiService: ApiService) {
    this.clientForm = this.fb.group({
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.loading = true;
    this.apiService.getClients().subscribe({
      next: clients => {
        this.clients = clients;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Impossible de charger les clients.';
        this.loading = false;
      }
    });
  }

  createClient(): void {
    if (this.clientForm.invalid) return;

    this.apiService.createClient(this.clientForm.value).subscribe({
      next: () => {
        this.clientForm.reset();
        this.loadClients();
      },
      error: () => this.errorMessage = 'Création du client impossible.'
    });
  }
}
