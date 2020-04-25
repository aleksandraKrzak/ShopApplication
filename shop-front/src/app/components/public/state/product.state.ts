import { PageProductDTO } from "./../../../../api/models/page-product-dto";
import { State, Action, StateContext } from "@ngxs/store";
import { LoadProductPageAction } from "./product.actions";
import { ProductControllerService } from "src/api/services";
import { tap, catchError } from "rxjs/operators";

export class ProductStateModel {
  public pageProductDTO: PageProductDTO;
}

@State<ProductStateModel>({
  name: "product",
  defaults: {
    pageProductDTO: null,
  },
})
export class ProductState {

  constructor(public productControllerService: ProductControllerService) { }
  // tap wykonuje sie wtedy kiedy dostajemy poprawna odpowiedz z backendu
  // ctx - kontkst stanu w ktorym jserstesmy, czyli productstate
  // ngxs - bibliotek do statemanagmentu
  @Action(LoadProductPageAction)
  loadProductPage(ctx: StateContext<ProductStateModel>, { page, size }: LoadProductPageAction) {
    return this.productControllerService.loadProductPageUsingGET({ page, size })
      .pipe(
        tap((response) => ctx.patchState({ pageProductDTO: response }))
      );
  }

}
