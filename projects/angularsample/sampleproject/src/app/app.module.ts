import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// import { HomwComponent } from './homw/homw.component';
// import { InfoComponent } from './info/info.component';

import { FormsModule } from '@angular/forms';
import { ParentCompComponent } from './parent-comp/parent-comp.component';
import { ChildCompComponent } from './child-comp/child-comp.component';




@NgModule({
  declarations: [
    AppComponent,
    ParentCompComponent,
    ChildCompComponent,
    // HomwComponent,
    // InfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

 }
