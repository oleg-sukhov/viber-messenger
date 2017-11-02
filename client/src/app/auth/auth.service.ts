import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {Observable} from 'rxjs/Observable'

@Injectable()
export class AuthService {
  public authToken: string

  constructor(private httpClient: HttpClient) {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'))
    this.authToken = currentUser && currentUser.token
  }

  login(username: string, password: string): Observable<boolean> {
    return Observable.create(observer => {
      if (username == 'os' && password == 'os') {
        localStorage.setItem('currentUser', JSON.stringify({username: username, authenticationToken: '123'}))
        observer.next(true)
      } else {
        this.authToken = null
        localStorage.removeItem('currentUser')
        observer.next(false)
      }
      observer.complete()
    });
  }

  logout(): void {
    this.authToken = null;
    localStorage.removeItem('currentUser')
  }

  isUserLoggedIn(): boolean {
    return localStorage.getItem('currentUser') != null
  }
}
