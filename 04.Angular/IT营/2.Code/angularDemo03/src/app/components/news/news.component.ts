import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css'],
})
export class NewsComponent implements OnInit {
  @ViewChild('footer') footer: any;

  constructor() {}

  ngOnInit(): void {}

  getChildMsg() {
    alert(this.footer.msg);
  }

  getChildRun() {
    this.footer.run();
  }

  // 接收子组件的信息
  run(e) {
    console.log(e); // 子组件传来的数据
  }
}
