import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css'],
})
export class FooterComponent implements OnInit {
  public msg = '我是子组件footer的msg';

  // 事件驱动
  @Output() private outer = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  run() {
    alert('我是子组件的run方法');
  }

  sendParent() {
    this.outer.emit('我是子组件的数据');
  }
}
