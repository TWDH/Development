import { Component, OnInit } from '@angular/core';

// 引入并配置服务
import { StorageService } from '../../services/storage.service';

/**
 * 普通引用
 * var storage = new StorageService();
 * console.log(storage);
 */

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent implements OnInit {
  // Attributes
  public keyword: string = '';

  public historyList: any[] = [];

  // 依赖注入
  constructor(public storage: StorageService) {}

  ngOnInit(): void {
    // 数据持久化
    var searchlist = this.storage.get('searchlist');
    if (searchlist) {
      this.historyList = searchlist;
    }
  }

  // 搜索按钮
  doSearch() {
    // 判断重复
    // 如果不存在，则加入列表
    if (this.historyList.indexOf(this.keyword) == -1) {
      this.historyList.push(this.keyword);

      // 将数据写入 localstorage 缓存
      this.storage.set('searchlist', this.historyList);
    }

    this.keyword = '';
    console.log(this.keyword);
  }

  // 删除历史记录
  deleteHistory(key) {
    // 从 key 位置，删除一个值
    this.historyList.splice(key, 1);
  }
}
