package controllers;

import lombok.extern.slf4j.Slf4j;
import models.Address;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.EmailService;

import javax.inject.Inject;
import java.util.List;

@Security.Authenticated(Secured.class)
@Slf4j
public class AddressController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    EmailService emailService;

    public Result index() {
        List<Address> addresses = Address.find.all();
        return ok(views.html.address.index.render(views.html.address.address.render(addresses), session().get("email")));
    }


    public Result create() {
        Form<Address> addressForm = formFactory.form(Address.class);

        return ok(views.html.address.index.render(views.html.address.create.render(addressForm), session().get("email")));
    }

    public Result save() {
        Form<Address> addressForm = formFactory.form(Address.class).bindFromRequest();

        if (addressForm.hasErrors()) {
            return badRequest(views.html.address.index.render(views.html.address.create.render(addressForm), session().get("email")));
        } else {
            Address address = addressForm.get();
            address.save();
            return redirect(routes.AddressController.index());
        }

    }

    public Result edit(Long id) {
        Address address = Address.find.byId(id);
        if (address == null) {
            return notFound("A kereset könyv nincs az adatbázisban");
        }
        Form<Address> addressForm = formFactory.form(Address.class).fill(address);

        return ok(views.html.address.index.render(views.html.address.edit.render(addressForm), session().get("email")));
    }

    public Result update() {
        Form<Address> addressForm = formFactory.form(Address.class).bindFromRequest();

        Address address = addressForm.get();
        address.setId(addressForm.get().getId());
        address.update();
        emailService.sendAddressEmail(address);
        return redirect(routes.AddressController.index());
    }

    public Result delete(Long id) {
        return ok();
    }

    public Result showAddress(Long id) {
        return ok();
    }

}
