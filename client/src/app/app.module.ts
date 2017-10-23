import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core'
import { BaseRequestOptions, HttpModule } from '@angular/http'
import { AppComponent } from './app.component'
import { FormsModule } from '@angular/forms'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { AppRouterModule } from './app.router.module'
import { AuthGuard } from './auth/auth.guard'
import { AuthService} from './auth/auth.service'
import { LoginModule } from './login/login.module'
import { DashboardModule } from './dashboard/dashboard.module'

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AppRouterModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    LoginModule,
    DashboardModule
  ],
  providers: [
    AuthGuard,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
