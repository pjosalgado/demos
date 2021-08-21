package tech.paulosalgado.grpc.springserver.service;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import tech.paulosalgado.grpc.HelloRequest;
import tech.paulosalgado.grpc.HelloResponse;
import tech.paulosalgado.grpc.HelloServiceGrpc;

@GRpcService
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
