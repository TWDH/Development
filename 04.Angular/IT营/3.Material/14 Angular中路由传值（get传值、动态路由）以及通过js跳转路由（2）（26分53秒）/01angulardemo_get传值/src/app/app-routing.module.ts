//配置路由的模块

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { NewsComponent } from './components/news/news.component';
import { HomeComponent } from './components/home/home.component';
import { ProductComponent } from './components/product/product.component';

import { NewscontentComponent } from './components/newscontent/newscontent.component';

const routes: Routes = [

  {

    path:'home',component:HomeComponent
  },

  {

    path:'news',component:NewsComponent
  },
  {

    path:'newscontent',component:NewscontentComponent
  },  
  {

    path:'product',component:ProductComponent
  },

  //匹配不到路由的时候加载的组件 或者跳转的路由
  {

    path:'**',redirectTo:'home'
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
