import { Store } from "@ngxs/store";
import { Component, OnInit } from "@angular/core";
import { FormlyFieldConfig } from "@ngx-formly/core";
import { FormGroup } from "@angular/forms";
import { RegisterAction } from "../state/user.actions";

@Component({
  selector: "app-registration",
  templateUrl: "./registration.component.html",
  styleUrls: ["./registration.component.sass"],
})
export class RegistrationComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({});

  registerFields: FormlyFieldConfig[] = [
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

    {
      key: "name",
      type: "input",
      templateOptions: {
        label: "Nickname",
        placeholder: "Please enter Your nickname",
        required: true,
      },
    },

    {
      key: "firstName",
      type: "input",
      templateOptions: {
        label: "Firstname",
        placeholder: "Please enter Your name",
        required: true,
      },
    },

    {
      key: "lastName",
      type: "input",
      templateOptions: {
        label: "Surname",
        placeholder: "Please enter Your surname",
        required: true,
      },
    },

    {
      key: "age",
      type: "input",
      templateOptions: {
        type: "number",
        label: "Age",
        placeholder: "Please enter Your age",
        required: true,
      },
    },
  ];

  constructor(public store: Store) { }

  ngOnInit() { }

  registration() {
    this.store.dispatch(
      new RegisterAction({
        firstName: this.registerForm.value.firstName,
        lastName: this.registerForm.value.lastName,
        name: this.registerForm.value.name,
        mail: this.registerForm.value.email,
        age: this.registerForm.value.age,
        password: this.registerForm.value.password,
      })
    );
  }
}
