import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  // 网络图片
  public picUrl = 'https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png'

  public list:any[] = [
    {
      "title":"我是家1"
    },
    {
      "title":"我是家2"
    },
    {
      "title":"我是家3"
    }
  ]

  public flag:boolean=false;

  // ngSwitch
  public orderStatus:number = 1;

  // [ngStyle]
  public attr:string='orange';

  // 管道符
  public today = new Date();

  public title:string = "I am a title";

  // 双向绑定
  public keywords:string = "这是默认值";


  constructor() { }

  ngOnInit(): void {
  }

  run(){
    alert("This is a 执行事件")
  }

  updateData(){
    this.title = "This is a updated title";
  }

  dom(event){
    // 获取当前 dom 节点
    var dom:any = event.target;
    dom.style.color='red'
  }

  // 监听键盘事件
  keyDown(e){
    console.log(e)
    // 获取当前 dom 对象值
    console.log(e.target.value)
  }

  changeKeywords(){
    this.keywords="I am changed"
  }

}


