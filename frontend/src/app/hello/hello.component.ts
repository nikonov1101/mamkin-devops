import { Component, OnInit } from '@angular/core';
import { HelloService, IListItem } from '../hello.service';

@Component({
  selector: 'app-hello',
  templateUrl: './hello.component.html',
  styleUrls: ['./hello.component.css']
})
export class HelloComponent implements OnInit {

  items: IListItem[] = [];
  constructor(private helloService: HelloService) { }

  ngOnInit() {
    this.helloService.getList().subscribe(list => this.items = list)
  }

}
