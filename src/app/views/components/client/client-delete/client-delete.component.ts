import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-client-delete',
  templateUrl: './client-delete.component.html',
  styleUrls: ['./client-delete.component.css']
})
export class ClientDeleteComponent implements OnInit {

  id_tec = "";

  client: Client = {
    id: "",
    name: "",
    cpf: "",
    phone: "",
  };

  constructor(
    private router: Router,
    private service: ClientService,
    private route: ActivatedRoute
  ) {}

 
 
  delete():void {
    this.service.delete(this.id_tec).subscribe({
      next: answer => {
        this.router.navigate(['clients'])
        this.service.message('Client deleted successfully!')
      }, 
      error: err => {
        console.log(err)
        if (err.error.error.match("Existing ")) {
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
      next: (answer) => (this.client = answer),
    });
  }

  cancel(): void {
    this.router.navigate(["clients"]);
  }
}

