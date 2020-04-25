import { LoginAction } from "./../state/user.actions";
import { Component, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { FormlyFieldConfig } from "@ngx-formly/core";
import { Store } from "@ngxs/store";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.sass"],
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({});

  loginFields: FormlyFieldConfig[] = [
    {
      key: "email",
      type: "input",
      templateOptions: {
        label: "Adress e-mail",
        placeholder: "Please enter Your adress e-mail",
        required: true,
      },
    },
    {
      key: "password",
      type: "input",
      templateOptions: {
        type: "password",
        label: "Password",
        placeholder: "Please enter Your password",
        required: true,
      },
    },
  ];

  constructor(public store: Store) { }

  ngOnInit() { }

  login() {
    this.store.dispatch(new LoginAction(this.loginForm.value.email, this.loginForm.value.password));
  }
}
