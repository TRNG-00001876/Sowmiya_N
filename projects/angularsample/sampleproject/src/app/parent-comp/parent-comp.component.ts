import { Component } from '@angular/core';

@Component({
  selector: 'app-parent-comp',
  templateUrl: './parent-comp.component.html',
  styleUrl: './parent-comp.component.css'
})
export class ParentCompComponent {

count=0;
message: string | undefined;
increment()
{
  this.count++;
}
decrement()
{
  this.count--;
}

handlechild(data:string){
  this.message=data;
  this.count=0;
}

}
