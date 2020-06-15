import { Component, OnInit } from '@angular/core';
import { HttpClientService, Company } from '../service/http-client.service';
import { ActivatedRoute } from '@angular/router';
import { FormsModule,Validators } from '@angular/forms';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit {
  today:any = Date.now();
  user = sessionStorage.getItem('username');
  company: Company = new Company("","","","","","",true,this.user,this.user,this.user,this.user,this.today,this.today,this.today,this.today,this.today);
  isEditing:boolean = false;
  

  constructor(private httpClientService: HttpClientService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = this.route.snapshot.params.code;
    console.log(id);

    if(id!= undefined){
      this.getCompanyRecordByCode(id);
      this.isEditing = true;
     
      
    }else{
      this.isEditing = false;
    }
  }

  createCompany(): void {
    console.log(this.company);
    this.company.ActiveDate=this.today;
    if(this.company.isActive){
      this.company.reactivatedOn=this.today;
    }else{
      this.company.deactivatedOn=this.today;
    }
    this.company.lastModifiedOn=this.today;
    this.httpClientService.createCompany(this.company)
        .subscribe( data => {
          alert("Company record created successfully.");
        });

  };
  
  resetCompany():void{
    this.company= new Company("","","","","","",true,this.user,this.user,this.user,this.user,this.today,this.today,this.today,this.today,this.today);
  
  }

  getCompanyRecordByCode(code): void {
    this.httpClientService.getCompanyRecordByCode(code)
    .subscribe( data => {
      //console.log(data);
      this.company = data;
    },
    error => {
      alert("Record does not exist. Please enter valid comapny code");
    }
    );


  };
  

}
