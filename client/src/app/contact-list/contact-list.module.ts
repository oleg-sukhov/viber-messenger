import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'
import {HttpClientModule} from '@angular/common/http'
import {FormsModule} from '@angular/forms'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import {ContactListComponent} from "./contact-list.component";
import {ContactListService} from "./contact-list.service";

@NgModule({
  declarations: [ContactListComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ContactListService],
  exports: [ContactListComponent],
  bootstrap: [ContactListComponent]
})
export class ContactListModule {
}
