import { Component, NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./views/components/home/home.component";
import { TechnicianReadComponent } from "./views/components/technician/technician-read/technician-read.component";
import { TechnicianCreateComponent } from "./views/components/technician/technician-create/technician-create.component";
import { TechnicianUpdateComponent } from "./views/components/technician/technician-update/technician-update.component";
import { TechnicianDeleteComponent } from "./views/components/technician/technician-delete/technician-delete.component";
import { ClientReadComponent } from "./views/components/client/client-read/client-read.component";
import { ClientCreateComponent } from "./views/components/client/client-create/client-create.component";
import { ClientUpdateComponent } from "./views/components/client/client-update/client-update.component";
import { ClientDeleteComponent } from "./views/components/client/client-delete/client-delete.component";
import { WoReadComponent } from "./views/components/wo/wo-read/wo-read.component";
import { WoCreateComponent } from "./views/components/wo/wo-create/wo-create.component";
import { WoUpdateComponent } from "./views/components/wo/wo-update/wo-update.component";
import { WoViewComponent } from "./views/components/wo/wo-view/wo-view.component";
import { WoClosedComponent } from "./views/components/wo/wo-closed/wo-closed.component";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
  },
  {
    path: "technicians",
    component: TechnicianReadComponent,
  },
  {
    path: "technicians/create",
    component: TechnicianCreateComponent,
  },
  {
    path: "technicians/update/:id",
    component: TechnicianUpdateComponent,
  },
  {
    path: "technicians/delete/:id",
    component: TechnicianDeleteComponent,
  },
  {
    path: "clients",
    component: ClientReadComponent,
  },
  {
    path: "clients/create",
    component: ClientCreateComponent,
  },
  {
    path: "clients/update/:id",
    component: ClientUpdateComponent,
  },
  {
    path: "clients/delete/:id",
    component: ClientDeleteComponent,
  },
  {
    path: "wo",
    component: WoReadComponent,
  },
  {
    path: "wo/create",
    component: WoCreateComponent,
  },
  {
    path: "wo/update/:id",
    component: WoUpdateComponent,
  },
  {
    path: "wo/view/:id",
    component: WoViewComponent,
  },
  {
    path: "wo/closed",
    component: WoClosedComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
