import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core'
import { MatToolbarModule, MatGridListModule, MatListModule } from '@angular/material'

import { AppComponent } from './app.component'

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    MatToolbarModule,
    MatGridListModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
