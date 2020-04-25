/* tslint:disable */
import { UserDTO } from './user-dto';
import { Pageable } from './pageable';
import { Sort } from './sort';
export interface PageUserDTO {
  content?: Array<UserDTO>;
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
