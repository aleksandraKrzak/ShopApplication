import { Observable } from "rxjs";
import { Store, Select } from "@ngxs/store";
import { Component, OnInit } from "@angular/core";
import { LoadProductPageAction } from "../state/product.actions";
import { PageProductDTO } from "src/api/models";

@Component({
  // ccontroller jak w springu
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.sass"],
})
export class ProductListComponent implements OnInit {

  @Select(state => state.product.pageProductDTO)
  pageProduct$: Observable<PageProductDTO>; // zmienna asynchroniczna moze sie ciagle zmmieniac

  displayedColumns: string[] = ["position", "name", "weight", "symbol"];

  constructor(public store: Store) { }

  ngOnInit() {
    this.store.dispatch(new LoadProductPageAction(0, 20));
  }

}
