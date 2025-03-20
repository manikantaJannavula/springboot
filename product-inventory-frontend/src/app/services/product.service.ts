import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = 'http://localhost:3000/products';

  constructor(private http: HttpClient) {}

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl).pipe(
      map(products => products.map(p => ({ ...p, id: Number(p.id) }))),
      catchError(this.handleError<Product[]>('getAllProducts', []))
    );
  }

  getProductById(id: number): Observable<Product | null> {
    return this.http.get<Product>(`${this.baseUrl}/${id}`).pipe(
      map(product => product ? { ...product, id: Number(product.id) } : null),
      catchError(() => {
        console.error(`Product with ID ${id} not found.`);
        return of(null);
      })
    );
  }

  createProduct(product: Product): Observable<Product> {
    return this.getAllProducts().pipe(
      map(products => {
        let maxId = 0;
        if (products && products.length > 0) {
          maxId = Math.max(...products.map(p => p.id));
        }
        return { ...product, id: maxId + 1 };
      }),
      switchMap(newProduct => this.http.post<Product>(this.baseUrl, newProduct)),
      catchError(this.handleError<Product>('createProduct'))
    );
  }

  updateProduct(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.baseUrl}/${id}`, { ...product, id: Number(id) }).pipe(
      catchError(this.handleError<Product>('updateProduct'))
    );
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${Number(id)}`).pipe(
      catchError(this.handleError<void>('deleteProduct'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}