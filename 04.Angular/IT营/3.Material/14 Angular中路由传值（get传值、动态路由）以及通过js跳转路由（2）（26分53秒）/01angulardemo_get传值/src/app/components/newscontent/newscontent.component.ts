/*
get传值

1、跳转
  <li *ngFor="let item of list;let key=index;">
      <!-- <a href="/newscontent?aid=123">{{key}}--{{item}}</a> -->
          
      <a [routerLink]="['/newscontent']" [queryParams]="{aid:key}">{{key}}--{{item}}</a>

    </li>



  2、接收

    import { ActivatedRoute } from '@angular/router';

    constructor(public route:ActivatedRoute) { }

   this.route.queryParams.subscribe((data)=>{

        console.log(data);
   })


*/


import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-newscontent',
  templateUrl: './newscontent.component.html',
  styleUrls: ['./newscontent.component.scss']
})
export class NewscontentComponent implements OnInit {

  constructor(public route:ActivatedRoute) { }

  ngOnInit() {

      // console.log(this.route.queryParams);
      this.route.queryParams.subscribe((data)=>{

        console.log(data);
      })
  }

}
