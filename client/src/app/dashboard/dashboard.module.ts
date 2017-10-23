import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core'
import { BaseRequestOptions, HttpModule } from '@angular/http'
import { FormsModule } from '@angular/forms'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { DashboardComponent } from './dashboard.component'
import { AccordionModule } from 'ngx-bootstrap/accordion'

@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    AccordionModule.forRoot()
  ],
  providers: [
  ],
  exports: [DashboardComponent],
  bootstrap: [DashboardComponent]
})
export class DashboardModule {
}
