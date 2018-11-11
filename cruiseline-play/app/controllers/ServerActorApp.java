package controllers;

import static akka.pattern.Patterns.ask;
import actor.ServerActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import play.libs.Akka;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;

public class ServerActorApp extends Controller {
  @SuppressWarnings("deprecation")
  private static ActorRef myActor = Akka.system().actorOf(new Props(ServerActor.class));

  public static Result process(String msg) {
    return async(Promise.wrap(ask(myActor, msg, 1000)).map(new Function<Object, Result>() {
      public Result apply(Object response) {
        return ok(response.toString());
      }
    }));
  }
}
