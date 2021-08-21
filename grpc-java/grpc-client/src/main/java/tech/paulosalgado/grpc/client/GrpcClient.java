package tech.paulosalgado.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import tech.paulosalgado.grpc.*;

import java.util.logging.Logger;

public class GrpcClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext()
                .build();

        hello(channel);
        saveContact(channel);

        channel.shutdown();
    }

    private static void hello(ManagedChannel channel) {

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Paulo")
                .setLastName("Salgado")
                .build());

        Logger.getGlobal().info(helloResponse.getGreeting());
    }

    private static void saveContact(ManagedChannel channel) {

        ContactServiceGrpc.ContactServiceBlockingStub stub = ContactServiceGrpc.newBlockingStub(channel);

        try {
            ContactResponse contactResponse = stub.save(ContactRequest.newBuilder()
                    .setName("Paulo Salgado")
//                    .setContact(MailContactRequest.newBuilder()
//                            .setEmail("pjosalgado@gmail.com")
//                            .build())
                    .build());

            Logger.getGlobal().info("Contact created: " + contactResponse.getId());

        } catch (StatusRuntimeException e) {
            String error = e.getMessage().replace(e.getStatus().getCode().name() + ": ", "");
            Logger.getGlobal().severe(e.getStatus().getCode().name() + " >> " + error);
        }
    }

}
