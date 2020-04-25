/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { PageProductDTO } from '../models/page-product-dto';
import { ProductDTO } from '../models/product-dto';

/**
 * Product Controller
 */
@Injectable({
  providedIn: 'root',
})
class ProductControllerService extends __BaseService {
  static readonly loadProductPageUsingGETPath = '/api/product';
  static readonly saveProductUsingPOSTPath = '/api/product';
  static readonly updateProductUsingPUT1Path = '/api/product';
  static readonly deleteProductUsingDELETEPath = '/api/product';
  static readonly processFileUsingPOSTPath = '/api/product/file';
  static readonly getByIdUsingGETPath = '/api/product/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param params The `ProductControllerService.LoadProductPageUsingGETParams` containing the following parameters:
   *
   * - `size`: size
   *
   * - `page`: page
   *
   * @return OK
   */
  loadProductPageUsingGETResponse(params: ProductControllerService.LoadProductPageUsingGETParams): __Observable<__StrictHttpResponse<PageProductDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.size != null) __params = __params.set('size', params.size.toString());
    if (params.page != null) __params = __params.set('page', params.page.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/product`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<PageProductDTO>;
      })
    );
  }
  /**
   * @param params The `ProductControllerService.LoadProductPageUsingGETParams` containing the following parameters:
   *
   * - `size`: size
   *
   * - `page`: page
   *
   * @return OK
   */
  loadProductPageUsingGET(params: ProductControllerService.LoadProductPageUsingGETParams): __Observable<PageProductDTO> {
    return this.loadProductPageUsingGETResponse(params).pipe(
      __map(_r => _r.body as PageProductDTO)
    );
  }

  /**
   * @param productDTO productDTO
   * @return OK
   */
  saveProductUsingPOSTResponse(productDTO: ProductDTO): __Observable<__StrictHttpResponse<ProductDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = productDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/product`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProductDTO>;
      })
    );
  }
  /**
   * @param productDTO productDTO
   * @return OK
   */
  saveProductUsingPOST(productDTO: ProductDTO): __Observable<ProductDTO> {
    return this.saveProductUsingPOSTResponse(productDTO).pipe(
      __map(_r => _r.body as ProductDTO)
    );
  }

  /**
   * @param productDTO productDTO
   * @return OK
   */
  updateProductUsingPUT1Response(productDTO: ProductDTO): __Observable<__StrictHttpResponse<ProductDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = productDTO;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/api/product`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProductDTO>;
      })
    );
  }
  /**
   * @param productDTO productDTO
   * @return OK
   */
  updateProductUsingPUT1(productDTO: ProductDTO): __Observable<ProductDTO> {
    return this.updateProductUsingPUT1Response(productDTO).pipe(
      __map(_r => _r.body as ProductDTO)
    );
  }

  /**
   * @param id id
   */
  deleteProductUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (id != null) __params = __params.set('id', id.toString());
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/product`,
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
  deleteProductUsingDELETE(id: number): __Observable<null> {
    return this.deleteProductUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @param multipartFile multipartFile
   */
  processFileUsingPOSTResponse(multipartFile: Blob): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let __formData = new FormData();
    __body = __formData;
    if (multipartFile != null) { __formData.append('multipartFile', multipartFile as string | Blob);}
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/product/file`,
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
   * @param multipartFile multipartFile
   */
  processFileUsingPOST(multipartFile: Blob): __Observable<null> {
    return this.processFileUsingPOSTResponse(multipartFile).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @param id id
   * @return OK
   */
  getByIdUsingGETResponse(id: number): __Observable<__StrictHttpResponse<ProductDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/product/${id}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProductDTO>;
      })
    );
  }
  /**
   * @param id id
   * @return OK
   */
  getByIdUsingGET(id: number): __Observable<ProductDTO> {
    return this.getByIdUsingGETResponse(id).pipe(
      __map(_r => _r.body as ProductDTO)
    );
  }
}

module ProductControllerService {

  /**
   * Parameters for loadProductPageUsingGET
   */
  export interface LoadProductPageUsingGETParams {

    /**
     * size
     */
    size: number;

    /**
     * page
     */
    page: number;
  }
}

export { ProductControllerService }
