import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css'],
  standalone: false
})
export class ProductFormComponent implements OnInit {
  productForm: FormGroup;
  isAddMode: boolean = true;
  productId: number | null = null;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      manufacturer: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      quantity: ['', [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.productId = idParam ? Number(idParam) : null;
    this.isAddMode = this.productId === null;

    if (!this.isAddMode && this.productId !== null) {
      this.loadProductDetails(this.productId);
    }
  }

  loadProductDetails(id: number): void {
    this.productService.getProductById(id).subscribe(
      (product) => {
        if (product) {
          this.productForm.patchValue(product);
        } else {
          this.errorMessage = 'Product not found.';
        }
      },
      (error) => {
        console.error('Error fetching product details:', error);
        this.errorMessage = 'Error fetching product details.';
      }
    );
  }

  onSubmit(): void {
    if (this.productForm.invalid) {
      Object.values(this.productForm.controls).forEach(control => control.markAsTouched());
      this.errorMessage = "Please fill in all required fields.";
      return;
    }

    const product = this.productForm.value;

    if (this.isAddMode) {
      this.createProduct(product);
    } else if (this.productId !== null) {
      this.updateProduct(this.productId, product);
    }
  }

  createProduct(product: any): void {
    this.productService.createProduct(product).subscribe(
      () => {
        alert('Product added successfully!');
        this.router.navigate(['/inventory']);
      },
      (error) => {
        console.error('Error creating product:', error);
        this.errorMessage = 'Error creating product. Please try again.';
      }
    );
  }

  updateProduct(id: number, product: any): void {
    this.productService.updateProduct(id, product).subscribe(
      () => {
        alert('Product updated successfully!');
        this.router.navigate(['/inventory']);
      },
      (error) => {
        console.error('Error updating product:', error);
        this.errorMessage = 'Error updating product. Please try again.';
      }
    );
  }

  get formControls() {
    return this.productForm.controls;
  }
}