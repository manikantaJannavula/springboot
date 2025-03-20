export interface User {
  id: number;
  email: string;
  password?: string; // Password is often write-only
  firstName: string;
  lastName: string;
  location: string;
  mobileNumber: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}