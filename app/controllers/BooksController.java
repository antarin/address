package controllers;

import lombok.extern.slf4j.Slf4j;
import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import services.EmailService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Security.Authenticated(Secured.class)
@Slf4j
public class BooksController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    EmailService emailService;

    public Result index() {
        List<Book> books = Book.find.all();
        return ok(views.html.books.index.render(views.html.books.books.render(books), session().get("email")));
    }


    public Result create() {
        Form<Book> bookForm = formFactory.form(Book.class);

        return ok(views.html.books.index.render(views.html.books.create.render(bookForm), session().get("email")));
    }

    public Result save() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();

        if (bookForm.hasErrors()) {
            return badRequest(views.html.books.index.render(views.html.books.create.render(bookForm), session().get("email")));
        } else {
            Book book = bookForm.get();
            book.save();
            return redirect(routes.BooksController.index());
        }

    }

    public Result edit(Long id) {
        Book book = Book.find.byId(id);
        if (book == null) {
            return notFound("A kereset könyv nincs az adatbázisban");
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);

        return ok(views.html.books.index.render(views.html.books.edit.render(bookForm), session().get("email")));
    }

    public Result update() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();

        Book book = bookForm.get();
        book.setId(bookForm.get().getId());
        book.update();
        emailService.sendEmail(book);
        return redirect(routes.BooksController.index());
    }

    public Result delete(Long id) {
        return ok();
    }

    public Result showBook(Long id) {
        return ok();
    }

}
