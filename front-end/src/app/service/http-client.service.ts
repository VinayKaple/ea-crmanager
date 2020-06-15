import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Timestamp } from 'rxjs';

export class Company{
  constructor(
    public code:string,
    public codeHRIS:string,
    public name:string,
    public abbrName:string,
    public regNo:string,
    public logo:string,
    public isActive:boolean,
    public createdBy:string,
    public lastModifiedBy:string,
    public deactivatedBy:string,
    public reactivatedBy:string,
    public createdOn:Date,
    public lastModifiedOn:Date,
    public deactivatedOn:Date,
    public reactivatedOn:Date,
    public ActiveDate:Date
  ){}
}
@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient:HttpClient) { }

  public getAllCompanyRecords(){
    console.log("get all records call");
    return this.httpClient.get<Company[]>('http://localhost:8080/records/company');
  }

  public deleteCompany(code) {
    return this.httpClient.delete<Company>("http://localhost:8080/records/company" + "/"+ code);
  }
  public createCompany(company){
    return this.httpClient.post<Company>("http://localhost:8080/records/company", company) ;
  }

  public getCompanyRecordByCode(code){
    return this.httpClient.get<Company>("http://localhost:8080/records/company" + "/"+ code);
  }
  public searchById(code){
    return this.httpClient.get<Company>("http://localhost:8080/records/company" + "/"+ code);
  }
}
