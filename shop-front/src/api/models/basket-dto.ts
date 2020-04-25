/* tslint:disable */
import { ProductDTO } from './product-dto';
import { UserDTO } from './user-dto';
export interface BasketDTO {
  id?: number;
  productDTO?: ProductDTO;
  quantity?: number;
  userDTO?: UserDTO;
}
