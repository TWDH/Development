import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  // Attributes
  public keyword:string = '';

  public historyList:any[] = [];


  constructor() { }

  ngOnInit(): void {
  }

  // 搜索按钮
  doSearch(){
    // 判断重复
    // 如果不存在，则加入列表
    if(this.historyList.indexOf(this.keyword)==-1){
      this.historyList.push(this.keyword);
    }

    this.keyword='';
    console.log(this.keyword)
  }

  // 删除历史记录
  deleteHistory(key){
    // 从 key 位置，删除一个值
    this.historyList.splice(key, 1);
  }

}
