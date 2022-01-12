import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  public peopleInfo: any = {
    username: 'He Zhu',
    gender: '1',
    cityList: ['Beijing', 'ShangHai', 'ShenZhen'],
    city: 'Beijing',
    // hobby 对象
    hobby: [
      {
        title: 'eat',
        checked: false,
      },
      {
        title: 'sleep',
        checked: false,

      },
      {
        title: 'coding',
        checked: false
      }
    ],
    mark: '',
  }

  constructor() {

  }

  ngOnInit(): void {
  }

  doSubmit() {
    // jQuery dom 获取表单中的值
    let nameDom: any = document.getElementById('username');
    console.log(nameDom.value)

    // 双向数据绑定
    console.log(this.peopleInfo)

  }

}
