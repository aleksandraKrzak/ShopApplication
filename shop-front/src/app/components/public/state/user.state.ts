import { State, Action, StateContext } from "@ngxs/store";
import { LoginAction, RegisterAction } from "./user.actions";
import { HttpClient } from "@angular/common/http";
import { tap } from "rxjs/operators";
import { MatSnackBar } from "@angular/material/snack-bar";
import { UserControllerService } from "src/api/services";
import { Navigate } from "@ngxs/router-plugin";

export class UserStateModel {
  public jwtToken: string;
}

@State<UserStateModel>({
  name: "user",
  defaults: {
    jwtToken: null,
  },
})
export class UserState {

  constructor(public httpClient: HttpClient, public snackBar: MatSnackBar, public userControllerService: UserControllerService) { }

  @Action(LoginAction)
  login(ctx: StateContext<UserStateModel>, { email, password }: LoginAction) {
    const formData: FormData = new FormData();
    formData.append("email", email);
    formData.append("password", password);

    return this.httpClient
      .post<{ authorization }>("http://localhost:8080/login", formData, {})
      .pipe(
        tap(({ authorization }) => {
          console.log(authorization);
          ctx.patchState({ jwtToken: authorization });
          this.snackBar.open("You have logged", "x", {
            duration: 10000,
            verticalPosition: "bottom",
            horizontalPosition: "center",
          });
        })
      );
  }

  @Action(RegisterAction)
  register(ctx: StateContext<UserStateModel>, { userDTO }: RegisterAction) {
    return this.userControllerService.saveUserUsingPOST(userDTO)
      .pipe(
        tap(response => ctx.dispatch(new Navigate(["/login"])))
      );
  }

}
