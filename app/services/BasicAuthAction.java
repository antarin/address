package services;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

@Slf4j
public class BasicAuthAction extends Action<Result> {

    private static final String AUTHORIZATION = "Authorization";
    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final String REALM = "Basic realm=\"Realm\"";

    @Override
    @SneakyThrows
    public CompletionStage<Result> call(Http.Context context) {
        Optional<String> auth = context.request().header(AUTHORIZATION);

        if (!auth.isPresent()) {
            context.response().setHeader(WWW_AUTHENTICATE, REALM);
            log.warn("Incorrect auth");
            return CompletableFuture.completedFuture(status(Http.Status.UNAUTHORIZED, "Needs authorization"));
        }

        String[] credentials;
        credentials = parseAuthHeader(auth.get());

        String username = credentials[0];
        String password = credentials[1];
        boolean loginCorrect = checkLogin(username, password);

        if (!loginCorrect) {
            log.warn("Incorrect basic auth login, username=" + username);
            return CompletableFuture.completedFuture(status(Http.Status.FORBIDDEN, "Forbidden"));
        } else {
            log.info("Successful basic auth login, username=" + username);
            return delegate.call(context);
        }
    }

    private String[] parseAuthHeader(String authHeader) throws UnsupportedEncodingException {
        if (!authHeader.startsWith("Basic ")) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }

        String[] credString;
        String auth = authHeader.substring(6);
        byte[] decodedAuth = new Base64().decode(auth);
        credString = new String(decodedAuth, "UTF-8").split(":", 2);
        if (credString.length != 2) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }

        return credString;
    }

    private boolean checkLogin(String username, String password) {
        return username.equals("mazsi") && password.equals("123456");
    }
}
