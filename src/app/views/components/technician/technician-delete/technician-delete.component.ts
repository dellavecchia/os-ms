import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Technician } from 'src/app/models/technician';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-technician-delete',
  templateUrl: './technician-delete.component.html',
  styleUrls: ['./technician-delete.component.css']
})
export class TechnicianDeleteComponent implements OnInit{

  id_tec = "";

  technician: Technician = {
    id: "",
    name: "",
    cpf: "",
    phone: "",
  };

  constructor(
    private router: Router,
    private service: TechnicianService,
    private route: ActivatedRoute
  ) {}

 
 
  delete():void {
    this.service.delete(this.id_tec).subscribe({
      next: answer => {
        this.router.navigate(['technicians'])
        this.service.message('Technician deleted successfully!')
      }, 
      error: err => {
        console.log(err)
        if (err.error.error.match("")) {
          this.service.message(err.error.error);
        }
      }
    })
  }
 
 
 
  ngOnInit(): void {
    this.id_tec = this.route.snapshot.paramMap.get("id")!;
    this.findById();
  }


  findById(): void {
    this.service.findById(this.id_tec).subscribe({
      next: (answer) => (this.technician = answer),
    });
  }

  cancel(): void {
    this.router.navigate(["technicians"]);
  }
}

