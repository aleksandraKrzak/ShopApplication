export class LoadProductPageAction {
  static readonly type = "[Product] LoadProductPageAction";
  constructor(public page: number, public size: number) { }
}
