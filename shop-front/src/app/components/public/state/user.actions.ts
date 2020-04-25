import { UserDTO } from "src/api/models";

export class LoginAction {
  static readonly type = "[User] LoginAction";
  constructor(public email: string, public password: string) { }
}

export class RegisterAction {
  static readonly type = "[User] RegisterAction";
  constructor(public userDTO: UserDTO) { }
}
