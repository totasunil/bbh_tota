FROM java:latest
ADD MessageStreamMicroServices_v2/readSwiftMessage-impl/target/universal /tmp/
RUN unzip /tmp/readswiftmessage-impl-1.0-SNAPSHOT.zip
ENTRYPOINT ["readswiftmessage-impl-1.0-SNAPSHOT/bin/readswiftmessage-impl"]
CMD []