import { Component, OnInit } from '@angular/core';
import { concat } from 'rxjs';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent implements OnInit {

  // Attributes
  public keyword:string = '';

  public todolist:any[] = [];
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

  doAdd(e){
    // 点击回车
    if(e.keyCode == 13){
      // 当前输入加入 todolist
      // 不存在则加入列表
      
      
      if(!this.todolistHasKeyword(this.todolist, this.keyword)){
          this.todolist.push({
          title: this.keyword,
          status: 0 // 0：待办事项；1：已完成事项
        });
      }
      else{
        alert("数据已存在");
        this.keyword='';
      }
      

      this.keyword=''
    }
  }

  deleteData(key){
    this.todolist.splice(key, 1)
  }

  // 如果数组里面有keyword返回true，否则返回false
  todolistHasKeyword(todolist:any, keyword:any){
    // 异步
    // todolist.foreach(value => {
    //   if(value.title == keyword){
    //     return true;
    //   }
    // })

    if(!keyword){
      return false;
    }

    for(var i = 0; i < todolist.length; i++){
      if(this.todolist[i].title == keyword){
          return true;
      }
      
    }

    return false;
  }

}
