import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Technician } from 'src/app/models/technician';
import { WO } from 'src/app/models/wo';
import { ClientService } from 'src/app/services/client.service';
import { TechnicianService } from 'src/app/services/technician.service';
import { WoService } from 'src/app/services/wo.service';

@Component({
  selector: 'app-wo-read',
  templateUrl: './wo-read.component.html',
  styleUrls: ['./wo-read.component.css']
})
export class WoReadComponent implements AfterViewInit {
 
  list: WO[] = [];

  displayedColumns: string[] = ['client','technician','openingDate','closingDate', 'priority', 'status', 'action'];
  dataSource = new MatTableDataSource<WO>(this.list);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service : WoService,
    private router : Router,
    private technicianService : TechnicianService,
    private clientService : ClientService ) {}

  ngAfterViewInit() {
    
    this.findAll();
  }

  findAll():void {
    this.service.findAll().subscribe((answer) => {
      answer.forEach(x => {
        if(x.status != "CLOSED") {
          this.list.push(x)
        }
      })
      this.technicianList();
      this.clientList();
      this.dataSource = new MatTableDataSource<WO>(this.list);
      this.dataSource.paginator = this.paginator;
    })
  }
//Navigate between routes
navigateToCreate():void {
  this.router.navigate(['wo/create'])

}
//Convert id to name
technicianList():void {
  this.list.forEach(x => {
    this.technicianService.findById(x.technician).subscribe({
      next: answer => {
        x.technician = answer.name
      }
    })
  })
}
clientList():void {
  this.list.forEach(x => {
    this.clientService.findById(x.client).subscribe({
      next: answer => {
        x.client = answer.name
      }
    })
  })
}
priority(x : any) {
  if(x == 'LOW'){
    return 'low'
  } else if(x == 'MEDIUM'){
  return 'medium'
}else {
  return 'high'
}


}

}