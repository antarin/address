package controllers;

import models.Login;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class LoginController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class);

        return ok(views.html.address.index.render(views.html.address.login.render(loginForm), session().get("email")));
    }

    public Result authenticate() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            loginForm.apply("email");
            return badRequest(views.html.address.index.render(views.html.address.login.render(loginForm), session().get("email")));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.AddressController.index()
            );
        }
    }

    public Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.LoginController.login()
        );
    }

}
