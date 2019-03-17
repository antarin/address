package services;


import models.Address;
import models.Book;
import play.api.libs.mailer.MailerClient;
import play.libs.mailer.Email;

import javax.inject.Inject;

public class EmailService {

    @Inject
    MailerClient mailerClient;

    public void sendEmail(Book book) {
        Email email = new Email()
                .setSubject("Simple email")
                .setFrom("miklos.rona@innovus.hu")
                .addTo("miklos.rona@gmail.com")
                .setBodyText("A(z) " + book.getBookTitle() + " című könyv adatait megváltoztatták. Az ára " + book.getPrice() + " és a szerzője " + book.getAuthor());
        mailerClient.send(email);
    }

    public void sendAddressEmail(Address address) {
        Email email = new Email()
                .setSubject("Simple email")
                .setFrom("miklos.rona@innovus.hu")
                .addTo("miklos.rona@gmail.com")
                .setBodyHtml("A(z) " + address.id + " azonosítójú cím megváltozott.<br>Az új cím a következő: <br> Irányítószám: " + address.getPostalCode() +
                        "<br>Város:  " + address.getCity() +
                        "<br>Közterület, házszám: " + address.getRestOfAddress() +
                        "<br><br>Üdvözlettel");
        mailerClient.send(email);
    }
}
