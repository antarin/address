package models;

import play.data.validation.Constraints;

@Constraints.Validate
public class Login implements Constraints.Validatable<String> {
    @Constraints.Required
    public String email;
    public String password;

    @Override
    public String validate() {
        if (!email.equals("mazsi@mazsi.hu") || !password.equals("123456")) {
            return "Invalid user or password";
        }

        return null;
    }
}
