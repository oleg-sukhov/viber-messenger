import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Contact} from "./contact";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ContactListService {

  constructor(private httpClient: HttpClient) {
  }

  public loadContacts(userId: string): Observable<Contact[]> {
    const headers = new HttpHeaders()
      .set('Authorization', 'Basic YWRtaW46YWRtaW4=')
    return this.httpClient.get<Contact[]>("http://localhost:8080/users/59f9e425415887c266424912/contacts", {headers: headers})
  }
}
