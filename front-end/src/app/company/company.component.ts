//import { Component, OnInit,ViewChild } from '@angular/core';
import { HttpClientService, Company } from '../service/http-client.service';
declare var $;
import { HttpClient, HttpResponse } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, ViewChild,Renderer2 } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  @ViewChild('dataTable') table;
  //dataTable: any;
  //dtOptions: DataTables.Settings = {};

  companyList:string[];
  

  constructor(private httpClientService:HttpClientService,private renderer: Renderer2, private router: Router,private http: HttpClient) { }

  ngOnInit(): void {

    

    this.httpClientService.getAllCompanyRecords().subscribe(
      response =>this.handleSuccessfulResponse(response)     );

  }
    
   
     
  
  deleteCompany(company: Company): void {
    if(confirm("Are you sure to delete "+company.name)) {
      console.log(company);
    this.httpClientService.deleteCompany(company.code)
      .subscribe( data => {
       // console.log(data);
        //this.companyList = this.companyList.filter(u => u !== company);
      })
      
    }
    
  };

  searchById(e){
    console.log("here");
    if(e.target.value!=""){
    this.httpClientService.searchById(e.target.value)
    .subscribe(response=>{
    
    
      
      this.handleSearchedResponse(response)},
      error=>{
       this.companyList=[];
      //  alert("No record found");
      }
      
      );
    }else{
      this.httpClientService.getAllCompanyRecords().subscribe(
        response =>this.handleSuccessfulResponse(response)     );
    }
  }
  handleSuccessfulResponse(response)
{
    this.companyList=response;
    console.log(this.companyList);
}
handleSearchedResponse(response)
{ var searched = [];
  searched.push(response);
  
    this.companyList=searched;
    
}


}
