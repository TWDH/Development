import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  public peopleInfo:any = {
    username:'He Zhu'
  }

  constructor() { }

  ngOnInit(): void {
  }

  doSubmit(){
    // jQuery dom 获取表单中的值
    let nameDom:any = document.getElementById('username');
    console.log(nameDom.value)

    // 双向数据绑定
    console.log(this.peopleInfo)

  }

}
