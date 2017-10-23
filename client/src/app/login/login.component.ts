import { Component, Input, OnInit } from '@angular/core'
import { Router } from '@angular/router'
import { AuthService } from '../auth/auth.service'

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    username: string;
    password: string;
    loading = false;
    messageError = '';

    constructor(private router: Router, private authService: AuthService) { }

    ngOnInit(): void {

    }

    login() {
        this.loading = true;
        this.authService.login(this.username, this.password).subscribe(result => {
                if (result === true) {
                    this.router.navigate(['/'])
                } else {
                    this.messageError = 'Username or password is incorrect'
                    this.loading = false
                }
            }, error => {
                console.log(error)
            });
    }
}
