/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { PageUserDTO } from '../models/page-user-dto';
import { UserDTO } from '../models/user-dto';

/**
 * User Controller
 */
@Injectable({
  providedIn: 'root',
})
class UserControllerService extends __BaseService {
  static readonly loadPageUserUsingGETPath = '/api/user';
  static readonly saveUserUsingPOSTPath = '/api/user';
  static readonly findUserByIdUsingGETPath = '/api/user/{id}';
  static readonly deleteUserUsingDELETEPath = '/api/user/{id}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param params The `UserControllerService.LoadPageUserUsingGETParams` containing the following parameters:
   *
   * - `size`: size
   *
   * - `page`: page
   *
   * @return OK
   */
  loadPageUserUsingGETResponse(params: UserControllerService.LoadPageUserUsingGETParams): __Observable<__StrictHttpResponse<PageUserDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.size != null) __params = __params.set('size', params.size.toString());
    if (params.page != null) __params = __params.set('page', params.page.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<PageUserDTO>;
      })
    );
  }
  /**
   * @param params The `UserControllerService.LoadPageUserUsingGETParams` containing the following parameters:
   *
   * - `size`: size
   *
   * - `page`: page
   *
   * @return OK
   */
  loadPageUserUsingGET(params: UserControllerService.LoadPageUserUsingGETParams): __Observable<PageUserDTO> {
    return this.loadPageUserUsingGETResponse(params).pipe(
      __map(_r => _r.body as PageUserDTO)
    );
  }

  /**
   * @param userDTO userDTO
   * @return OK
   */
  saveUserUsingPOSTResponse(userDTO: UserDTO): __Observable<__StrictHttpResponse<UserDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userDTO;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/api/user`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDTO>;
      })
    );
  }
  /**
   * @param userDTO userDTO
   * @return OK
   */
  saveUserUsingPOST(userDTO: UserDTO): __Observable<UserDTO> {
    return this.saveUserUsingPOSTResponse(userDTO).pipe(
      __map(_r => _r.body as UserDTO)
    );
  }

  /**
   * @param id id
   * @return OK
   */
  findUserByIdUsingGETResponse(id: number): __Observable<__StrictHttpResponse<UserDTO>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/user/${id}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDTO>;
      })
    );
  }
  /**
   * @param id id
   * @return OK
   */
  findUserByIdUsingGET(id: number): __Observable<UserDTO> {
    return this.findUserByIdUsingGETResponse(id).pipe(
      __map(_r => _r.body as UserDTO)
    );
  }

  /**
   * @param id id
   */
  deleteUserUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/api/user/${id}`,
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
  deleteUserUsingDELETE(id: number): __Observable<null> {
    return this.deleteUserUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }
}

module UserControllerService {

  /**
   * Parameters for loadPageUserUsingGET
   */
  export interface LoadPageUserUsingGETParams {

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

export { UserControllerService }
