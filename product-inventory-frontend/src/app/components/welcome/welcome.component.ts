import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css'],
  standalone: false
})
export class WelcomeComponent implements OnInit {
  products: Product[] = [];
  searchTerm: string = '';
  errorMessage: string = '';
  isLoggedIn: boolean = false;

  constructor(
    private productService: ProductService,
    private router: Router,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadProducts();
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe(
      (products: Product[]) => {
        this.products = products.map(p => ({ ...p, id: Number(p.id) })); // Ensure IDs are numbers
      },
      (error) => {
        console.error('Error fetching products:', error);
        this.errorMessage = 'Error loading products. Please try again later.';
      }
    );
  }

  viewProduct(id: number | undefined): void {
    if (id !== undefined) {
      this.router.navigate(['/product-detail', id]);
    }
  }

  editProduct(id: number | undefined): void {
    if (id !== undefined) {
      if (this.isLoggedIn) {
        this.router.navigate(['/update-product', id]);
      } else {
        this.router.navigate(['/auth/sign-in']);
      }
    }
  }

  deleteProduct(id: number | undefined): void {
    if (id !== undefined) {
      if (this.isLoggedIn) {
        if (confirm('Are you sure you want to delete this product?')) {
          this.productService.deleteProduct(Number(id)).subscribe( // Ensure id is number
            () => {
              this.loadProducts();
            },
            (error) => {
              console.error('Error deleting product:', error);
              this.errorMessage = 'Error deleting product. Please try again.';
            }
          );
        }
      } else {
        this.router.navigate(['/auth/sign-in']);
      }
    }
  }

  addProduct(): void {
    if (this.isLoggedIn) {
      this.router.navigate(['/add-product']);
    } else {
      this.router.navigate(['/auth/sign-in']);
    }
  }

  get filteredProducts(): Product[] {
    return this.products.filter((product) =>
      product.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}