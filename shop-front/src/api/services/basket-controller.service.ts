/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { BasketDTO } from '../models/basket-dto';

/**
 * Basket Controller
 */
@Injectable({
  providedIn: 'root',
})
class BasketControllerService extends __BaseService {
  static readonly getBasketByUserUsingGETPath = '/api/basket';
  static readonly addProductUsingPOSTPath = '/api/basket';
  static readonly updateProductUsingPUTPath = '/api/basket';
  static readonly deleteByProductIDUsingDELETEPath = '/api/basket';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @return OK
   */
  getBasketByUserUsingGETResponse(): __Observable<__StrictHttpResponse<Array<BasketDTO>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/basket`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<BasketDTO>>;
      })
    );
  }
  /**
   * @return OK
   */
  getBasketByUserUsingGET(): __Observable<Array<BasketDTO>> {
    return this.getBasketByUserUsingGETResponse().pipe(
      __map(_r => _r.body as Array<BasketDTO>)
    );
  }

  /**
   * @param basketDTO basketDTO
   * @return OK
   */
  addProductUsingPOSTResponse(basketDTO: BasketDTO): __Observable<__StrictHttpResponse<BasketDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = basketDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/basket`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<BasketDTO>;
      })
    );
  }
  /**
   * @param basketDTO basketDTO
   * @return OK
   */
  addProductUsingPOST(basketDTO: BasketDTO): __Observable<BasketDTO> {
    return this.addProductUsingPOSTResponse(basketDTO).pipe(
      __map(_r => _r.body as BasketDTO)
    );
  }

  /**
   * @param params The `BasketControllerService.UpdateProductUsingPUTParams` containing the following parameters:
   *
   * - `userDTO.password`:
   *
   * - `userDTO.name`:
   *
   * - `userDTO.mail`:
   *
   * - `userDTO.lastName`:
   *
   * - `userDTO.id`:
   *
   * - `userDTO.firstName`:
   *
   * - `userDTO.age`:
   *
   * - `quantity`:
   *
   * - `productDTO.quantity`:
   *
   * - `productDTO.price`:
   *
   * - `productDTO.name`:
   *
   * - `productDTO.id`:
   *
   * - `productDTO.description`:
   *
   * - `id`:
   *
   * @return OK
   */
  updateProductUsingPUTResponse(params: BasketControllerService.UpdateProductUsingPUTParams): __Observable<__StrictHttpResponse<BasketDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.userDTOPassword != null) __params = __params.set('userDTO.password', params.userDTOPassword.toString());
    if (params.userDTOName != null) __params = __params.set('userDTO.name', params.userDTOName.toString());
    if (params.userDTOMail != null) __params = __params.set('userDTO.mail', params.userDTOMail.toString());
    if (params.userDTOLastName != null) __params = __params.set('userDTO.lastName', params.userDTOLastName.toString());
    if (params.userDTOId != null) __params = __params.set('userDTO.id', params.userDTOId.toString());
    if (params.userDTOFirstName != null) __params = __params.set('userDTO.firstName', params.userDTOFirstName.toString());
    if (params.userDTOAge != null) __params = __params.set('userDTO.age', params.userDTOAge.toString());
    if (params.quantity != null) __params = __params.set('quantity', params.quantity.toString());
    if (params.productDTOQuantity != null) __params = __params.set('productDTO.quantity', params.productDTOQuantity.toString());
    if (params.productDTOPrice != null) __params = __params.set('productDTO.price', params.productDTOPrice.toString());
    if (params.productDTOName != null) __params = __params.set('productDTO.name', params.productDTOName.toString());
    if (params.productDTOId != null) __params = __params.set('productDTO.id', params.productDTOId.toString());
    if (params.productDTODescription != null) __params = __params.set('productDTO.description', params.productDTODescription.toString());
    if (params.id != null) __params = __params.set('id', params.id.toString());
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/basket`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<BasketDTO>;
      })
    );
  }
  /**
   * @param params The `BasketControllerService.UpdateProductUsingPUTParams` containing the following parameters:
   *
   * - `userDTO.password`:
   *
   * - `userDTO.name`:
   *
   * - `userDTO.mail`:
   *
   * - `userDTO.lastName`:
   *
   * - `userDTO.id`:
   *
   * - `userDTO.firstName`:
   *
   * - `userDTO.age`:
   *
   * - `quantity`:
   *
   * - `productDTO.quantity`:
   *
   * - `productDTO.price`:
   *
   * - `productDTO.name`:
   *
   * - `productDTO.id`:
   *
   * - `productDTO.description`:
   *
   * - `id`:
   *
   * @return OK
   */
  updateProductUsingPUT(params: BasketControllerService.UpdateProductUsingPUTParams): __Observable<BasketDTO> {
    return this.updateProductUsingPUTResponse(params).pipe(
      __map(_r => _r.body as BasketDTO)
    );
  }

  /**
   * @param id id
   */
  deleteByProductIDUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (id != null) __params = __params.set('id', id.toString());
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/basket`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }
  /**
   * @param id id
   */
  deleteByProductIDUsingDELETE(id: number): __Observable<null> {
    return this.deleteByProductIDUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module BasketControllerService {

  /**
   * Parameters for updateProductUsingPUT
   */
  export interface UpdateProductUsingPUTParams {
    userDTOPassword?: string;
    userDTOName?: string;
    userDTOMail?: string;
    userDTOLastName?: string;
    userDTOId?: number;
    userDTOFirstName?: string;
    userDTOAge?: number;
    quantity?: number;
    productDTOQuantity?: number;
    productDTOPrice?: number;
    productDTOName?: string;
    productDTOId?: number;
    productDTODescription?: string;
    id?: number;
  }
}

export { BasketControllerService }
