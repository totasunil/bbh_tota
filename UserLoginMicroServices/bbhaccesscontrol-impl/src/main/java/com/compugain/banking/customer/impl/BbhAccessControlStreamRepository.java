package com.compugain.banking.customer.impl;

/**
 * Created by sunny.singh on 5/31/2017.
 */

import akka.Done;
import com.compugain.banking.customer.api.Role;
import com.compugain.banking.customer.api.User;
import com.lightbend.lagom.javadsl.persistence.cassandra.CassandraSession;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@Singleton
public class BbhAccessControlStreamRepository {
    private final CassandraSession uninitialisedSession;

    // Will return the session when the Cassandra tables have been successfully created
    private volatile CompletableFuture<CassandraSession> initialisedSession;

    @Inject
    public BbhAccessControlStreamRepository(CassandraSession uninitialisedSession) {
        this.uninitialisedSession = uninitialisedSession;
        // Eagerly create the session
        session();
    }

    private CompletionStage<CassandraSession> session() {
        // If there's no initialised session, or if the initialised session future completed
        // with an exception, then reinitialise the session and attempt to create the tables
        if (initialisedSession == null || initialisedSession.isCompletedExceptionally()) {
            initialisedSession = uninitialisedSession.executeCreateTable(
                    "CREATE TABLE IF NOT EXISTS user (" +
                            "    user_id text," +
                            "    customer_Id text," +
                            "    first_name text," +
                            "    last_name text," +
                            "    email text," +
                            "    user_name text," +
                            "    password text," +
                            "    is_disabled text," +
                            "    role map<int,text>," +
                            "    PRIMARY KEY ((user_id), customer_Id)" +
                            ") "


            ).thenApply(done -> uninitialisedSession).toCompletableFuture();

            initialisedSession = uninitialisedSession.executeCreateTable(
                    "CREATE TABLE IF NOT EXISTS role (" +
                            "    role_id text," +
                            "    role_name text," +
                            "    role_desc text," +
                            "    PRIMARY KEY (role_id)" +
                            ") "


            ).thenApply(done -> uninitialisedSession).toCompletableFuture();
        }
        return initialisedSession;
    }

    public CompletionStage<Done> addUser(User user) {
        return session().thenCompose(session ->
                session.executeWrite("INSERT INTO user(" +
                                "user_id, customer_Id, first_name, last_name, email,user_name,password,is_disabled,role)" +
                                "VALUES (?, ?, ?, ?, ?,?,?,?,?)",
                        user.user_id, user.customer_Id, user.first_name, user.last_name, user.email, user.user_name, user.password,
                        user.is_disabled, user.role
                )
        );
    }

    public CompletionStage<Done> assignRole(User user) {
        return session().thenCompose(session ->
                session.executeWrite("INSERT INTO user(" +
                                "user_id, customer_Id, first_name, last_name, email,user_name,password,is_disabled,role)" +
                                "VALUES (?, ?, ?, ?, ?,?,?,?,?)",
                        user.user_id, user.customer_Id, user.first_name, user.last_name, user.email, user.user_name, user.password,
                        user.is_disabled, user.role
                )
        );
    }

    public CompletionStage<Done> addRole(Role role) {
        return session().thenCompose(session ->
                session.executeWrite("INSERT INTO role(" +
                                "role_id, role_name, role_desc)" +
                                "VALUES (?, ?, ?)",
                        role.role_id, role.role_name, role.role_desc)
        );
    }
}
