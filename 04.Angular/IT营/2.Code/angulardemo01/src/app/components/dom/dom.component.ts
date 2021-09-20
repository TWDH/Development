import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dom',
  templateUrl: './dom.component.html',
  styleUrls: ['./dom.component.scss'],
})
export class DomComponent implements OnInit {
  flag: boolean = true;

  constructor() {}

  ngOnInit(): void {
    // 组件和指令初始化完成，并不是真正的dom加载完成
    let oBox: any = document.getElementById('box');
    // 打印 dom 里面的数据
    console.log(oBox.innerHTML);
    // 将 dom 颜色改变
    oBox.style.color = 'red';

    // 再次获取dom; 不可读，因为 dom 在 ngOnInit 没有加载完成
    /* let oBox2: any = document.getElementById('box1');
    console.log(oBox2.innterHTML);
    oBox2.style.color = 'blue'; */
  }

  // 视图加载完成后，触发；dom加载完成，建议dom操作放在这里
  ngAfterViewInit(): void {
    let oBox2: any = document.getElementById('box1');
    console.log(oBox2.innterHTML);
    oBox2.style.color = 'blue';
  }
}
