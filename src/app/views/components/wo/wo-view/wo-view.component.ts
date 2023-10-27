import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { WO } from 'src/app/models/wo';
import { WoService } from 'src/app/services/wo.service';

@Component({
  selector: 'app-wo-view',
  templateUrl: './wo-view.component.html',
  styleUrls: ['./wo-view.component.css']
})
export class WoViewComponent implements OnInit {

  
  wo: WO = {
    technician: "",
    client: "",
    comments: "",
    priority: "",
    status: "",

  };

  constructor(
    private route: ActivatedRoute,
    private service: WoService,
    private router: Router) { }

  ngOnInit(): void {
    this.wo.id = this.route.snapshot.paramMap.get("id");
    this.findById();
  }

  findById():void {
    this.service.findById(this.wo.id).subscribe(resposta => {
      this.wo = resposta;
      this.findById();
    })
  }

  return(): void {
    this.router.navigate(['wo'])
  }

}