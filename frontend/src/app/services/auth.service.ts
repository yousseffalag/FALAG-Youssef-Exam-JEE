import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { LoginResponse } from '../models/api.models';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private token: string | null = null;
  private scope = '';
  private username = '';

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.backendHost}/auth/login`, { username, password })
      .pipe(tap(response => this.saveSession(response, username)));
  }

  logout(): void {
    this.token = null;
    this.scope = '';
    this.username = '';
    localStorage.removeItem('access-token');
    localStorage.removeItem('scope');
    localStorage.removeItem('username');
    this.router.navigateByUrl('/login');
  }

  loadJwtTokenFromLocalStorage(): void {
    this.token = localStorage.getItem('access-token');
    this.scope = localStorage.getItem('scope') || '';
    this.username = localStorage.getItem('username') || '';
  }

  getToken(): string | null {
    if (!this.token) {
      this.loadJwtTokenFromLocalStorage();
    }
    return this.token;
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  getUsername(): string {
    if (!this.username) {
      this.loadJwtTokenFromLocalStorage();
    }
    return this.username;
  }

  getCurrentRole(): string {
    if (!this.scope) {
      this.loadJwtTokenFromLocalStorage();
    }
    return this.getUserRoles()[0] || 'ANONYMOUS';
  }

  getCurrentRoleLabel(): string {
    const currentRole = this.getCurrentRole();
    switch (currentRole) {
      case 'ROLE_ADMIN':
        return 'Administrateur';
      case 'ROLE_EMPLOYE':
        return 'Employé';
      case 'ROLE_CLIENT':
        return 'Client';
      default:
        return 'Invité';
    }
  }

  getUserRoles(): string[] {
    if (!this.scope) {
      this.loadJwtTokenFromLocalStorage();
    }
    return this.scope ? this.scope.split(' ') : [];
  }

  hasRole(role: 'ROLE_CLIENT' | 'ROLE_EMPLOYE' | 'ROLE_ADMIN'): boolean {
    return this.getUserRoles().includes(role);
  }

  hasAnyRole(roles: string[]): boolean {
    return roles.some(role => this.getUserRoles().includes(role));
  }

  getClientId(): number | null {
    const username = this.getUsername();
    if (username === 'client') {
      return 1;
    }
    return null;
  }

  getDefaultRoute(): string {
    if (this.isClient()) {
      return '/paiements';
    }
    if (this.isEmploye()) {
      return '/contrats';
    }
    if (this.isAdmin()) {
      return '/clients';
    }
    return '/login';
  }

  isAdmin(): boolean {
    return this.hasRole('ROLE_ADMIN');
  }

  isEmploye(): boolean {
    return this.hasRole('ROLE_EMPLOYE');
  }

  isClient(): boolean {
    return this.hasRole('ROLE_CLIENT');
  }

  private saveSession(response: LoginResponse, username: string): void {
    this.token = response['access-token'];
    this.scope = response.scope;
    this.username = username;
    localStorage.setItem('access-token', this.token);
    localStorage.setItem('scope', this.scope);
    localStorage.setItem('username', this.username);
  }
}
