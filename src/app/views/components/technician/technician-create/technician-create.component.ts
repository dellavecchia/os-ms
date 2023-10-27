import { Component } from "@angular/core";
import { FormControl, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Technician } from "src/app/models/technician";
import { TechnicianService } from "src/app/services/technician.service";

@Component({
  selector: "app-technician-create",
  templateUrl: "./technician-create.component.html",
  styleUrls: ["./technician-create.component.css"],
})
export class TechnicianCreateComponent {
  technician: Technician = {
    id: "",
    name: "",
    cpf: "",
    phone: "",
  };
//Form validadion
  name = new FormControl("", [Validators.minLength(5)]);
  cpf = new FormControl("", [Validators.minLength(11)]);
  phone = new FormControl("", [Validators.minLength(10)]);

  constructor(private router: Router, private service: TechnicianService) {}

  // Button cancel on create tech
  cancel(): void {
    this.router.navigate(["technicians"]);
  }

  create(): void {
    this.service.create(this.technician).subscribe({
      next: (answer) => {
        console.log(answer);
        this.router.navigate(["technicians"]);
        this.service.message("Technician created successfully");
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
