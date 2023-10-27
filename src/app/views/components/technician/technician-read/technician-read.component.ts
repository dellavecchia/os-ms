import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Technician } from 'src/app/models/technician';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-technician-read',
  templateUrl: './technician-read.component.html',
  styleUrls: ['./technician-read.component.css']
})
export class TechnicianReadComponent implements AfterViewInit {
 
  technicians: Technician[] = [];

  displayedColumns: string[] = ['id', 'name', 'cpf', 'phone', 'action'];
  dataSource = new MatTableDataSource<Technician>(this.technicians);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service : TechnicianService,
    private router : Router) {}

  ngAfterViewInit() {
    
    this.findAll();
  }

  findAll():void {
    this.service.findAll().subscribe((answer) => {
      this.technicians = answer;
      this.dataSource = new MatTableDataSource<Technician>(this.technicians);
      this.dataSource.paginator = this.paginator;
    })
  }
//Navigate between routes
navigateToCreate():void {
  this.router.navigate(['technicians/create'])

}

}

