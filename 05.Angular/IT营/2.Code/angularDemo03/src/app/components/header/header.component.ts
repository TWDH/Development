import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  // 接收父组件的传值
  @Input() title: any;

  @Input() msg: any;

  @Input() run: any;

  @Input() home: any;

  constructor() {}

  ngOnInit(): void {}

  getParentMsg() {
    alert(this.title);
  }

  getParentRun() {
    // 执行父组件的run方法
    this.run();
  }

  getParentAll() {
    // 父组件整个实例传给子组件
    this.home.run();
  }
}
