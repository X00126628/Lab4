package models.users;

import models.users.User;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
/**
 * Created by wdd on 03/02/17.
 */
public class AuthAdmin extends Action.Simple{



    public CompletionStage<Result> call(Http.Context ctx){


        String id = ctx.session().get("email");
        if (id != null){

            User u = User.getUserById(id);
            if ("admin".equals(u.getRole())){


                return delegate.call(ctx);
            }

        }

        ctx.flash().put("error", "Admin Login Required.");
        return CompletableFuture.completedFuture(redirect("@routes.LoginController.login()"));

    }
}
