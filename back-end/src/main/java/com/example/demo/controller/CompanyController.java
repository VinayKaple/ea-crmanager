package com.example.demo.controller;

import com.example.demo.model.Company;
import com.example.demo.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/records")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping(value = "/company")
    public ResponseEntity<Object> getAllCompanies(){

        if(companyRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No records exist!");
        }
        else{
            List<Company> companyList = new ArrayList<>();
            companyRepository.findAll().forEach(companyList::add);
            return ResponseEntity.ok().body(companyList);
        }
    }

    @GetMapping(value = "/company/{companyCode}")
    public ResponseEntity<Object> getCompanyById(@PathVariable String companyCode){
        if(companyRepository.findById(companyCode).isPresent()){
            Company companyCopy = companyRepository.findById(companyCode).get();
            return ResponseEntity.ok().body(companyCopy);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company record requested to be read does not exist!");
        }
    }

    @PostMapping(value = "/company")
    public ResponseEntity addCompany(@RequestBody Company company){
        try{

            if(companyRepository.findById(company.getCode()).isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't create record. Record with given company code already exist.");
            }
            else{
            companyRepository.save(company);
            }
        }
        catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mandatory fields are missing!");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Record created.");
    }

    @PutMapping(value = "/company/{companyCode}")
    public ResponseEntity modifyCompany(@RequestBody Company company, @PathVariable String companyCode){

        if(companyRepository.findById(companyCode).isPresent()) {
            Company companyCopy = companyRepository.findById(companyCode).orElse(null);

            companyCopy.setName(Optional.ofNullable(company.getName()).orElse(companyCopy.getName()));
            companyCopy.setAbbrName(Optional.ofNullable(company.getAbbrName()).orElse(companyCopy.getAbbrName()));
            companyCopy.setRegNo(Optional.ofNullable(company.getRegNo()).orElse(companyCopy.getRegNo()));
            companyCopy.setLogo(Optional.ofNullable(company.getLogo()).orElse(companyCopy.getLogo()));
            companyCopy.setActiveDate(Optional.ofNullable(company.getActiveDate()).orElse(companyCopy.getActiveDate()));
            companyCopy.setIsActive(Optional.ofNullable(company.getIsActive()).orElse(companyCopy.getIsActive()));
            companyCopy.setCreatedOn(Optional.ofNullable(company.getCreatedOn()).orElse(companyCopy.getCreatedOn()));
            companyCopy.setCreatedBy(Optional.ofNullable(company.getCreatedBy()).orElse(companyCopy.getCreatedBy()));
            companyCopy.setLastModifiedOn(Optional.ofNullable(company.getLastModifiedOn()).orElse(companyCopy.getLastModifiedOn()));
            companyCopy.setLastModifiedBy(Optional.ofNullable(company.getLastModifiedBy()).orElse(companyCopy.getLastModifiedBy()));
            companyCopy.setDeactivatedOn(Optional.ofNullable(company.getDeactivatedOn()).orElse(companyCopy.getDeactivatedOn()));
            companyCopy.setDeactivatedBy(Optional.ofNullable(company.getDeactivatedBy()).orElse(companyCopy.getDeactivatedBy()));
            companyCopy.setReactivatedOn(Optional.ofNullable(company.getReactivatedOn()).orElse(companyCopy.getReactivatedOn()));
            companyCopy.setReactivatedBy(Optional.ofNullable(company.getReactivatedBy()).orElse(companyCopy.getReactivatedBy()));

            companyRepository.save(companyCopy);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company record requested to be updated does not exist!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Record updated.");
    }

    @DeleteMapping("/company/{companyCode}")
    public ResponseEntity removeCompany(@PathVariable String companyCode){
        try{
            companyRepository.deleteById(companyCode);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company record requested to be deleted does not exist!");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Record deleted.");
    }
}
