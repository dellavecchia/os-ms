import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Client } from "src/app/models/client";
import { Technician } from "src/app/models/technician";
import { WO } from "src/app/models/wo";
import { ClientService } from "src/app/services/client.service";
import { TechnicianService } from "src/app/services/technician.service";
import { WoService } from "src/app/services/wo.service";

@Component({
  selector: "app-wo-create",
  templateUrl: "./wo-create.component.html",
  styleUrls: ["./wo-create.component.css"],
})
export class WoCreateComponent implements OnInit {
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
    private router: Router
  ) {}

  ngOnInit(): void {
    this.technicianList();
    this.clientList();
  }

  create(): void {
    this.woService.create(this.wo).subscribe((answer) => {
      this.woService.message("Work order created successfully!");
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
}
