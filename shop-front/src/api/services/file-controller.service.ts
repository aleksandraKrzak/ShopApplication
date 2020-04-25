/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';


/**
 * File Controller
 */
@Injectable({
  providedIn: 'root',
})
class FileControllerService extends __BaseService {
  static readonly generateProductsFileUsingGETPath = '/api/file';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param fileType fileType
   * @return OK
   */
  generateProductsFileUsingGETResponse(fileType?: 'PDF' | 'XLS' | 'DOC' | 'JSON' | 'XML' | 'CSV'): __Observable<__StrictHttpResponse<string>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (fileType != null) __params = __params.set('fileType', fileType.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/api/file`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<string>;
      })
    );
  }
  /**
   * @param fileType fileType
   * @return OK
   */
  generateProductsFileUsingGET(fileType?: 'PDF' | 'XLS' | 'DOC' | 'JSON' | 'XML' | 'CSV'): __Observable<string> {
    return this.generateProductsFileUsingGETResponse(fileType).pipe(
      __map(_r => _r.body as string)
    );
  }
}

module FileControllerService {
}

export { FileControllerService }
