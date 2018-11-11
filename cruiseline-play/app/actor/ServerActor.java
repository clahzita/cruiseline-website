package actor;

import akka.actor.*;

public class ServerActor extends UntypedActor{
  @Override
  public void onReceive(Object arg0) throws Exception{
    if(arg0 instanceof String){
      getSender().tell(arg0 + " - got something from server", getSelf());

    }
  }
}
