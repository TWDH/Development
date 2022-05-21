import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  // home 传值到 header
  public title: string = 'Home Component Title';

  public msg: string = 'Home Component MSG';

  constructor() {}

  ngOnInit(): void {}

  run() {
    alert('我是父组件的run方法');
  }
}
