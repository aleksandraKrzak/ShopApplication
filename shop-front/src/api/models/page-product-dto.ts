/* tslint:disable */
import { ProductDTO } from './product-dto';
import { Pageable } from './pageable';
import { Sort } from './sort';
export interface PageProductDTO {
  content?: Array<ProductDTO>;
  empty?: boolean;
  first?: boolean;
  last?: boolean;
  number?: number;
  numberOfElements?: number;
  pageable?: Pageable;
  size?: number;
  sort?: Sort;
  totalElements?: number;
  totalPages?: number;
}
