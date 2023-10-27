import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { Technician } from 'src/app/models/technician';
import { WO } from 'src/app/models/wo';
import { ClientService } from 'src/app/services/client.service';
import { TechnicianService } from 'src/app/services/technician.service';
import { WoService } from 'src/app/services/wo.service';

@Component({
  selector: 'app-wo-update',
  templateUrl: './wo-update.component.html',
  styleUrls: ['./wo-update.component.css']
})
export class WoUpdateComponent implements OnInit {
  wo: WO = {
    technician: "",
    client: "",
    comments: "",
    status: "",
    priority: "",
  };

  technicians: Technician[] = [];
  clients: Client[] = [];

  constructor(
    private technicianService: TechnicianService,
    private clienteService: ClientService,
    private woService: WoService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.wo.id = this.route.snapshot.paramMap.get('id');
    this.findById();
    this.technicianList();
    this.clientList();
    

  }

  findById():void {
    this.woService.findById(this.wo.id).subscribe(answer => {
      this.wo = answer;
      this.dataConverter();
    })
  }

  update(): void {
    this.woService.update(this.wo).subscribe((answer) => {
      this.woService.message("Work order updated successfully!");
      this.router.navigate(["wo"]);
    });
  }
  cancel(): void {
    this.router.navigate(["wo"]);
  }

  technicianList(): void {
    this.technicianService.findAll().subscribe((answer) => {
      this.technicians = answer;
    });
  }

  clientList(): void {
    this.clienteService.findAll().subscribe((answer) => {
      this.clients = answer;
    });
  }

  dataConverter():void {
    if(this.wo.status == "OPEN") {
      this.wo.status = 0 ;
    } else if(this.wo.status == "ONGOING"){
      this.wo.status = 1;
    }else {
      this.wo.status = 2;
    }

    if (this.wo.priority == "LOW"){
      this.wo.priority = 0;
    } else if (this.wo.priority == "MEDIUM"){
      this.wo.priority = 1;
    } else {
      this.wo.priority = 2;
    }
  }
}
