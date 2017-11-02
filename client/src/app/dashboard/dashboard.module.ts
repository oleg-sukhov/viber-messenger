import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'
import {FormsModule} from '@angular/forms'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import {DashboardComponent} from './dashboard.component'
import {AccordionModule} from 'ngx-bootstrap/accordion'
import {HttpClientModule} from "@angular/common/http";
import {ContactListModule} from "../contact-list/contact-list.module";

@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ContactListModule,
    AccordionModule.forRoot()
  ],
  providers: [],
  exports: [DashboardComponent],
  bootstrap: [DashboardComponent]
})
export class DashboardModule {
}
