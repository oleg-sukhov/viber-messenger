import {Component, OnInit} from '@angular/core'
import {ContactListService} from "./contact-list.service";
import {Contact} from "./contact";

@Component({
  selector: 'contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

  contacts: Contact[]

  constructor(private contactListService: ContactListService) {
  }

  ngOnInit(): void {
    this.contactListService.loadContacts("userId").subscribe(loadedContacts => {
      this.contacts = [];
      loadedContacts.forEach(contact => this.contacts.push(contact))
    });
  }
}
