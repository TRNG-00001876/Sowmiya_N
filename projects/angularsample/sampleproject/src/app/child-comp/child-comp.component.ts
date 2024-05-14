import { Component,Input ,Output} from '@angular/core';
import { EventEmitter } from 'node:stream';

@Component({
  selector: 'app-child-comp',
  templateUrl: './child-comp.component.html',
  styleUrl: './child-comp.component.css'
})
export class ChildCompComponent {
 @Input() count_child : number =0;
 @Output() btn_click = new EventEmitter<string>();
 clear_count()
 {
  this.btn_click.emit("clear the count");
 }
}
