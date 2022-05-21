import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})

export class NewsComponent implements OnInit {

  /**
   * 定义普通属性
   */
  // 这是属性，不是变量 var
  // 定义属性, {{}}绑定数据
  public title = "I am a news title";

  // alternatively
  msg = "I am msg";

  // ts指定类型
  username: string = 'zhangsan';

  // 推荐：任意类型数据
  public student: any = 25;

  // 对象
  // 获取数据：{{userinfo.username}}
  public userinfo: object = {
    username: "zhangsan",
    age: '20'
  }

  // 未赋值属性
  public message: any;

  // html标签
  public content = "<h2>This is a html</h2>";

  /**
   * 定义数组
   */
  public arr = ['1', '2', '3'];

  //推荐
  public list: any[] = ['4', '5', '6']

  public items: Array<number> = [7, 8, 9]

  // 数组中包含对象
  public userlist: any[] = [
    {
      username: '张三',
      age: 20
    },
    {
      username: "李四",
      age: 25
    }]

  // 复杂数组
  public cars: any[] = [
    {
      category: "BMW",
      details:[
        {
          type: "BMW X3",
          price: "50w"
        },
        {
          type: "BMW X3",
          price: "50w"
        },
        {
          type: "BMW X3",
          price: "50w"
        }
      ]
    },

    {
      category: "AUDI",
      details:[
        {
          type: "AUDI Q5",
          price: "50w"
        },
        {
          type: "AUDI Q5",
          price: "50w"
        },
        {
          type: "AUDI Q5",
          price: "50w"
        }
      ]
    }
  ]







  constructor() {

    // 获取属性的值
    console.log(this.message);

    // 给属性赋值，改变属性; <div> 中取值时得到改变后的属性
    this.message = "这是给属性赋值 -- 改变属性的值";

  }

  ngOnInit(): void {
  }

}
