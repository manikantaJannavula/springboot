import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, switchMap, tap } from 'rxjs/operators';
import { User, LoginRequest } from '../models/user.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:3000/users';

  constructor(private http: HttpClient, private router: Router) {}

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl).pipe(
      map(users => users.map(u => ({ ...u, id: Number(u.id) }))),
      catchError(this.handleError<User[]>('getAllUsers', []))
    );
  }

  register(user: User): Observable<any> {
    return this.getAllUsers().pipe(
      map(users => {
        let maxId = 0;
        if (users && users.length > 0) {
          maxId = Math.max(...users.map(u => u.id));
        }
        return { ...user, id: maxId + 1 };
      }),
      switchMap(newUser => this.http.post<User>(this.baseUrl, newUser)),
      map(() => ({ success: true, message: 'Registration successful' })),
      catchError((error: HttpErrorResponse) => {
        if (error.error?.message?.includes('already registered')) {
          return of({ success: false, message: 'Email already registered' });
        }
        console.error('Registration error:', error);
        return of({ success: false, message: 'Registration failed. Please try again.' });
      })
    );
  }

  login(loginRequest: LoginRequest): Observable<any> {
    return this.getAllUsers().pipe(
      map(users =>
        users.find(user => user.email === loginRequest.email && user.password === loginRequest.password)
      ),
      tap(user => {
        if (user) {
          this.setLoggedIn(true);
        }
      }),
      catchError(error => {
        console.error('Login error:', error);
        return of({ success: false, message: 'Invalid credentials' });
      }),
      map(user => (user ? { success: true } : { success: false, message: 'Invalid credentials' }))
    );
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('isLoggedIn') === 'true';
  }

  setLoggedIn(value: boolean): void {
    localStorage.setItem('isLoggedIn', value.toString());
  }

  logout(): void {
    localStorage.removeItem('isLoggedIn');
    this.router.navigate(['/login']);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}