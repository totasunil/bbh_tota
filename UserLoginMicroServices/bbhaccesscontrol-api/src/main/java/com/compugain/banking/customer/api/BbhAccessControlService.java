package com.compugain.banking.customer.api;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;
import org.pcollections.PSequence;
import java.util.Optional;
import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;

public interface BbhAccessControlService extends Service{
    ServiceCall<User,  String> newUser();
    ServiceCall<Role, NotUsed> newRole();
    ServiceCall<User, NotUsed> assignRoleToUser();
    ServiceCall<UserAuthentication, Optional<User>> userAuthentication();
    ServiceCall<NotUsed, PSequence<Role>> getAllCustomerACDetails();

    default Descriptor descriptor(){
        return named("bbhAccessControlService").withCalls(
                restCall(Method.POST,"/api/createUser", this::newUser)
                ,restCall(Method.POST,"/api/createRole", this::newRole)
                 ,restCall(Method.PUT,"/api/assignRole", this::assignRoleToUser)
                 ,restCall(Method.POST,"/api/userAuthentication", this::userAuthentication),
                restCall(Method.GET,"/api/getallcusaccount/", this::getAllCustomerACDetails)
        ).withAutoAcl(true);
    }
}

