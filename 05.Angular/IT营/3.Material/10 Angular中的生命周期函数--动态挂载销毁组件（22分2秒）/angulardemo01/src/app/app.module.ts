import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

//双休数据绑定必须引入
import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { LifecycleComponent } from './components/lifecycle/lifecycle.component';


@NgModule({
  declarations: [
    AppComponent,
    LifecycleComponent,    
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
