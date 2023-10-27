import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-client-create',
  templateUrl: './client-create.component.html',
  styleUrls: ['./client-create.component.css']
})
export class ClientCreateComponent {

  client: Client = {
    id: "",
    name: "",
    cpf: "",
    phone: "",
  };
//Form validadion
  name = new FormControl("", [Validators.minLength(5)]);
  cpf = new FormControl("", [Validators.minLength(11)]);
  phone = new FormControl("", [Validators.minLength(10)]);

  constructor(private router: Router, private service: ClientService) {}
  
  // Button cancel on create tech
  cancel(): void {
    this.router.navigate(["clients"]);
  }

  create(): void {
    this.service.create(this.client).subscribe({
      next: (answer) => {
        console.log(answer);
        this.router.navigate(["clients"]);
        this.service.message("Client created successfully");
      },
      error: (err) => {
        if (err.error.error.match("already exists")) {
          this.service.message(err.error.error);
        } else if (
          err.error.errors[0].message ===
          "invalid Brazilian individual taxpayer registry number (CPF)"
        ) {
          this.service.message("Invalid CPF!");
        }
      },
    });
  }

  errorValidName() {
    if (this.name.invalid) {
      return "The name must have at lest 5 characters.";
    }
    return false;
  }

  errorValidCpf() {
    if (this.cpf.invalid) {
      return "The cpf must have at least 11 characters.";
    }
    return false;
  }

  errorValidPhone() {
    if (this.phone.invalid) {
      return "The phone must have at least 12 characters.";
    }
    return false;
  }
}
