import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-viewchild',
  templateUrl: './viewchild.component.html',
  styleUrls: ['./viewchild.component.scss'],
})
export class ViewchildComponent implements OnInit {
  // 获取 dom 节点，并赋值给 myBox
  @ViewChild('myBox') myBox: any;
  // 获取子组件
  @ViewChild('header') header: any;

  constructor() {}

  ngOnInit(): void {}

  ngAfterViewInit(): void {
    // dom 节点存在了 nativeElement 中
    console.log(this.myBox.nativeElement);
    this.myBox.nativeElement.style.background = 'yellow';
    console.log(this.myBox.nativeElement.innerHTML);
  }

  getChildFunc() {
    // 调用子组件的方法
    this.header.run();
  }
}
