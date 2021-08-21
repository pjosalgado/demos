package tech.paulosalgado.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import tech.paulosalgado.grpc.server.service.ContactServiceImpl;
import tech.paulosalgado.grpc.server.service.HelloServiceImpl;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8080)
                .addService(ProtoReflectionService.newInstance())
                .addService(new HelloServiceImpl())
                .addService(new ContactServiceImpl())
                .build();

        server.start();
        server.awaitTermination();
    }

}
