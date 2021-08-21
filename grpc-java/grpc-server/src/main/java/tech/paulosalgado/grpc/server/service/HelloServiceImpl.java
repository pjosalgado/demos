package tech.paulosalgado.grpc.server.service;

import io.grpc.stub.StreamObserver;
import tech.paulosalgado.grpc.HelloRequest;
import tech.paulosalgado.grpc.HelloResponse;
import tech.paulosalgado.grpc.HelloServiceGrpc;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greeting = "Hello, " + request.getFirstName() + " " + request.getLastName();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
