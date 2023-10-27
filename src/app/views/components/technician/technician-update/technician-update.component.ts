import { Component, OnInit } from "@angular/core";
import { FormControl, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Technician } from "src/app/models/technician";
import { TechnicianService } from "src/app/services/technician.service";

@Component({
  selector: "app-technician-update",
  templateUrl: "./technician-update.component.html",
  styleUrls: ["./technician-update.component.css"],
})
export class TechnicianUpdateComponent implements OnInit {
  id_tec = "";

  technician: Technician = {
    id: "",
    name: "",
    cpf: "",
    phone: "",
  };

  name = new FormControl("", [Validators.minLength(5)]);
  cpf = new FormControl("", [Validators.minLength(11)]);
  phone = new FormControl("", [Validators.minLength(10)]);

  constructor(
    private router: Router,
    private service: TechnicianService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id_tec = this.route.snapshot.paramMap.get("id")!;
    this.findById();
  }


  update(): void{
    this.service.update(this.technician).subscribe({
      next: answer => {
        this.router.navigate(['technicians'])
        this.service.message("Technician updated successfully!")
      },
      error: (err) => {
        console.log(err);
        if (err.error.error.match("already exists")) {
          this.service.message(err.error.error);
        } else if (
          err.error.errors[0].message ===
          "invalid Brazilian individual taxpayer registry number (CPF)"
        ) {
          this.service.message("Invalid CPF!");
        }
      },
    })
  }

  findById(): void {
    this.service.findById(this.id_tec).subscribe({
      next: (answer) => (this.technician = answer),
    });
  }

  cancel(): void {
    this.router.navigate(["technicians"]);
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
