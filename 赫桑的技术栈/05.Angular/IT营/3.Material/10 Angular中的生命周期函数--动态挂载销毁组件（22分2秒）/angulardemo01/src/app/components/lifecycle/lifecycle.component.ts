import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-lifecycle',
  templateUrl: './lifecycle.component.html',
  styleUrls: ['./lifecycle.component.scss'],
})
export class LifecycleComponent implements OnInit {
  @Input('title') title: any;

  public msg: string = '我是一个生命周期演示';

  public userinfo: string = '';

  public oldUserinfo: string = '';

  constructor() {
    console.log(
      '00构造函数执行了---除了使用简单的值对局部变量进行初始化之外，什么都不应该做'
    );
  }

  ngOnChanges() {
    console.log(
      '01ngOnChages执行了---当被绑定的输入属性的值发生变化时调用(父子组件传值的时候会触发)'
    );
  }
  ngOnInit() {
    console.log('02ngOnInit执行了--- 请求数据一般放在这个里面');
  }
  ngDoCheck() {
    //写一些自定义的操作
    console.log(
      '03ngDoCheck执行了---检测，并在发生 Angular 无法或不愿意自己检测的变化时作出反应'
    );
    if (this.userinfo !== this.oldUserinfo) {
      console.log(`你从${this.oldUserinfo}改成${this.userinfo}`);
      this.oldUserinfo = this.userinfo;
    } else {
      console.log('数据没有变化');
    }
  }
  ngAfterContentInit() {
    console.log('04ngAfterContentInit执行了---当把内容投影进组件之后调用');
  }
  ngAfterContentChecked() {
    console.log(
      '05ngAfterContentChecked执行了---每次完成被投影组件内容的变更检测之后调用'
    );
  }
  ngAfterViewInit(): void {
    console.log(
      '06 ngAfterViewInit执行了----初始化完组件视图及其子视图之后调用（dom操作放在这个里面）'
    );
  }
  ngAfterViewChecked() {
    console.log(
      '07ngAfterViewChecked执行了----每次做完组件视图和子视图的变更检测之后调用'
    );
  }

  ngOnDestroy() {
    console.log('08ngOnDestroy执行了····');
  }

  //自定义方法

  changeMsg() {
    this.msg = '数据改变了';
  }
}
