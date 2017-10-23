import { Component } from '@angular/core'

@Component({
  selector: 'app-root',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
}
