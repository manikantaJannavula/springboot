import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
  standalone: false
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  searchTerm: string = '';

  constructor(
    private productService: ProductService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe(
      (products: Product[]) => {
        this.products = products.map(p => ({ ...p, id: Number(p.id) })); // Ensure IDs are numbers
      },
      (error) => {
        console.error('Error fetching products:', error);
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
      this.router.navigate(['/update-product', id]);
    }
  }

  deleteProduct(id: number | undefined): void {
    if (id !== undefined && confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(Number(id)).subscribe( // Ensure id is number
        () => {
          this.loadProducts();
        },
        (error) => {
          console.error('Error deleting product:', error);
        }
      );
    }
  }

  addProduct(): void {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/add-product']);
    } else {
      this.router.navigate(['/auth/sign-in']);
    }
  }

  get filteredProducts(): Product[] {
    return this.products.filter(product =>
      product.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}