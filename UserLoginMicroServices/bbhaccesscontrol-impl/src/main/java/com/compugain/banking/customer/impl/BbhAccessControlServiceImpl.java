package com.compugain.banking.customer.impl;

import akka.Done;
import akka.NotUsed;
import com.compugain.banking.customer.api.BbhAccessControlService;
import com.compugain.banking.customer.api.Role;
import com.compugain.banking.customer.api.User;
import com.compugain.banking.customer.api.UserAuthentication;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.deser.ExceptionMessage;
import com.lightbend.lagom.javadsl.api.transport.TransportErrorCode;
import com.lightbend.lagom.javadsl.api.transport.TransportException;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.lightbend.lagom.javadsl.persistence.cassandra.CassandraSession;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;
import com.lightbend.lagom.javadsl.server.ServerServiceCall;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class BbhAccessControlServiceImpl implements BbhAccessControlService {

    private final PersistentEntityRegistry persistentEntityRegistry;
    private final CassandraSession db;
    private final BbhAccessControlStreamRepository repository;

    @Inject
    public BbhAccessControlServiceImpl(PersistentEntityRegistry persistentEntityRegistry, BbhAccessControlStreamRepository repository, CassandraSession db) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        this.repository = repository;

        persistentEntityRegistry.register(BbhAccessControlPersistentEntity.class);
        persistentEntityRegistry.register(BbhAccessControlRolePersistentEntity.class);
        this.db = db;
        //readSide.register(BbhAccessControlReadSideProcessor.class);
        // readSide.register(BbhAccessControlRoleReadSideProcessor.class);
    }

    private <Response> Response handleException(Throwable e) {
        if (e.getCause() != null && e.getCause() instanceof PersistentEntity.InvalidCommandException) {
            throw new TransportException(TransportErrorCode.BadRequest,
                    new ExceptionMessage("BadRequest", e.getCause().getMessage()));
        } else {

            throw new Error(e);
        }
    }

    // Wrapping invalid requests...

    private <Request, Response> ServerServiceCall<Request, Response> validate(
            ServerServiceCall<Request, Response> serviceCall) {

        return HeaderServiceCall.compose(requestHeader ->
                (req) -> serviceCall.invoke(req)
                        .exceptionally(this::handleException)
        );
    }

    // Implemntation of new user creation

    @Override
    public ServiceCall<User, String> newUser() {

        return validate((request) -> {
            CompletionStage<Optional<User>> result
                    = db.selectOne("SELECT * FROM user WHERE user_name= '" + request.user_name + "' ALLOW FILTERING ")
                    .thenApply(row ->
                            row.map(r -> new User(r.getString("user_id"), r.getString("customer_Id"), r.getString("first_name"), r.getString("last_name"),
                                    r.getString("email"), r.getString("user_name"), r.getString("password"), r.getString("is_disabled"), Optional.of(r.getMap("role", Integer.class, String.class)))
                            )).toCompletableFuture();

            PersistentEntityRef<BbhAccessControlCommand> ref = persistentEntityRegistry.refFor(BbhAccessControlPersistentEntity.class, request.user_name);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            return result.thenApply(re -> {
                if (re.isPresent()) {
                    String fail = "User is already existed";
                    return fail;
                } else {
                    repository.addUser(request);
                    String success = "User is already existed";
                    return success;
                }
            });
        });
    }

    @Override
    public ServiceCall<UserAuthentication, Optional<User>> userAuthentication() {
        return validate((request) -> {
            CompletionStage<Optional<User>> result
                    = db.selectOne("SELECT * FROM user WHERE user_name= '" + request.user_name + "' and password='" + request.password + "'  ALLOW FILTERING ")
                    .thenApply(row ->
                            row.map(r -> new User(r.getString("user_id"), r.getString("customer_Id"), r.getString("first_name"), r.getString("last_name"),
                                    r.getString("email"), r.getString("user_name"), r.getString("password"), r.getString("is_disabled"), Optional.of(r.getMap("role", Integer.class, String.class)))
                            ));
            return result;
        });
    }

    //@Override
    public ServiceCall<UserAuthentication, String> userAuthentication1() {
        return validate((request) -> {
            CompletionStage<Optional<User>> result = db.selectOne("SELECT * FROM user WHERE user_name= '" + request.user_name + "' and password='" + request.password + "'  ALLOW FILTERING ")
                    .thenApply(row -> row.map(r -> new User(r.getString("user_id"), r.getString("customer_Id"), r.getString("first_name"), r.getString("last_name"), r.getString("email"), r.getString("user_name"), r.getString("password"), r.getString("is_disabled"), Optional.empty()))
                    );
            return result.thenApply(User -> {
                if (User.isPresent()) {
                    return " User Authentication is Valid";
                } else {
                    return "Invalid User Please enter correct Details";
                }
            });
        });

    }

    @Override
    public ServiceCall<User, NotUsed> assignRoleToUser() {

        return validate((request) -> {
            // Look up the  role entity for the given ID.
            PersistentEntityRef<BbhAccessControlCommand> ref = persistentEntityRegistry.refFor(BbhAccessControlPersistentEntity.class, request.user_id);

            CompletionStage<Done> results = repository.assignRole(request);

            return results.thenApply(res -> {
                return null;
            });
        });

    }

    @Override
    public ServiceCall<NotUsed, PSequence<Role>> getAllCustomerACDetails() {
        return (req) -> {
            CompletionStage<PSequence<Role>> result
                    = db.selectAll("SELECT * FROM role")
                    .thenApply(rows -> {
                        List<Role> list = rows.stream().map(r -> new Role(r.getString("role_id"), r.getString("role_name"), r.getString("role_desc"))).collect(Collectors.toList());
                        return TreePVector.from(list);
                    });

            return result;
        };
    }

    @Override
    public ServiceCall<Role, NotUsed> newRole() {

        return validate((request) -> {
            // Look up the  account entity for the given ID.
            PersistentEntityRef<BbhAccessControlRoleCommand> ref = persistentEntityRegistry.refFor(BbhAccessControlRolePersistentEntity.class, request.role_id);
            CompletionStage<Done> results = repository.addRole(request);

            return results.thenApply(res -> {
                return null;
            });
        });
    }
}