import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  constructor(private employeeService : EmployeeService,
    private router : Router) { }
  employee : Employee = new Employee();

  ngOnInit(): void {
  }

  onSubmit(){
    this.saveEmployee();
    console.log(this.employee)
    this.goToEmployeeList();
  }
  saveEmployee(){
    this.employeeService.createEmployee(this.employee).subscribe(data =>{
      console.log(data);
    },
    error => console.log(error));
  }

  goToEmployeeList(){
    this.router.navigate(['/employees']);
  }


}
