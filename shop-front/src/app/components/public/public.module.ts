import { UserState } from "./state/user.state";
import { NgxsModule } from "@ngxs/store";
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { LoginComponent } from "./login/login.component";
import { Routes, RouterModule } from "@angular/router";
import { RegistrationComponent } from "./registration/registration.component";
import { FormlyModule } from "@ngx-formly/core";
import { ReactiveFormsModule } from "@angular/forms";
import { FormlyMaterialModule } from "@ngx-formly/material";
import { ProductListComponent } from "./product-list/product-list.component";
import { MatButtonModule } from "@angular/material/button";
import { HttpClientModule } from "@angular/common/http";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { ProductState } from "./state/product.state";
import { MatTableModule } from "@angular/material/table";

const routes: Routes = [
  {
    path: "login",
    component: LoginComponent,
  },

  {
    path: "registration",
    component: RegistrationComponent,
  },

  {
    path: "products",
    component: ProductListComponent,
  },
];

@NgModule({
  declarations: [LoginComponent, RegistrationComponent, ProductListComponent],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
    FormlyModule.forRoot(),
    ReactiveFormsModule,
    FormlyMaterialModule,
    MatButtonModule,
    HttpClientModule,
    NgxsModule.forRoot([UserState, ProductState]),
    MatSnackBarModule,
    MatTableModule,
  ],
})
export class PublicModule { }
