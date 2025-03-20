import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { ProductFormComponent } from './components/product-form/product-form.component'; // Import ProductFormComponent
import { ProductDetailComponent } from './components/product-detail/product-detail.component'; // Import ProductDetailComponent

const routes: Routes = [
  { path: '', redirectTo: '/welcome', pathMatch: 'full' },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'inventory', component: ProductListComponent }, // Changed 'product-list' to 'inventory'
  { path: 'auth/sign-in', component: LoginComponent },
  { path: 'auth/register', component: RegisterComponent },
  { path: 'add-product', component: ProductFormComponent }, // Add route for adding products
  { path: 'update-product/:id', component: ProductFormComponent }, // Route for updating products
  { path: 'product-detail/:id', component: ProductDetailComponent }  //Route for product details
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }