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

动态路由


      1、配置动态路由

       {

        path:'newscontent/:aid',component:NewscontentComponent
      }


      2、跳转


        <ul>
        <li *ngFor="let item of list;let key=index;">

          <a [routerLink]="[ '/newscontent/', key ]">{{key}}---{{item}}</a>

        </li>
      </ul>


      3、接收

       import { ActivatedRoute } from '@angular/router';

        constructor(public route:ActivatedRoute) { }

        this.route.params.subscribe((data)=>{

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

    
     /*

     get传值
      this.route.queryParams.subscribe((data)=>{

        console.log(data);
      })

     
     */
       //获取动态路由传值
       this.route.params.subscribe((data)=>{
        console.log(data);
      })
  
  }

}
