import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  standalone: false
})
export class RegisterComponent {
  registerForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      location: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern("^[6789][0-9]{9}$")]]
    });
  }

  register(): void {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value).subscribe(
        (response) => {
          if (response && response.success) {
            alert('Registration Successful!');
            this.router.navigate(['auth/sign-in']);
          } else {
            this.errorMessage = response?.message || 'Registration failed.';
          }
        },
        (error) => {
          if (error.status === 400) {
            this.handleValidationErrors(error.error);
          } else {
            console.error('Registration error:', error);
            this.errorMessage = 'An error occurred during registration.';
          }
        }
      );
    } else {
      Object.values(this.registerForm.controls).forEach(control => {
        control.markAsTouched();
      });
    }
  }

  handleValidationErrors(errors: any): void {
    if (typeof errors === 'string') {
      alert(errors);
    } else if (typeof errors === 'object') {
      let errorMessage = '';
      for (const key in errors) {
        if (errors.hasOwnProperty(key)) {
          errorMessage += `${errors[key]}\n`;
        }
      }
      alert(errorMessage.trim());
    } else {
      alert('Validation error occurred.');
    }
  }
}