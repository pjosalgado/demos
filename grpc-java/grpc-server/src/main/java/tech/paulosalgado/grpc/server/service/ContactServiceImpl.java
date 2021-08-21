package tech.paulosalgado.grpc.server.service;

import com.google.common.base.Strings;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import tech.paulosalgado.grpc.ContactRequest;
import tech.paulosalgado.grpc.ContactResponse;
import tech.paulosalgado.grpc.ContactServiceGrpc;
import tech.paulosalgado.grpc.MailContactResponse;

import java.util.UUID;

public class ContactServiceImpl extends ContactServiceGrpc.ContactServiceImplBase {

    @Override
    public void save(ContactRequest request, StreamObserver<ContactResponse> responseObserver) {

        if (!validate(request, responseObserver)) {
            return;
        }

        ContactResponse response = ContactResponse.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setName(request.getName())
                .setContact(MailContactResponse.newBuilder()
                        .setEmail(request.getContact().getEmail())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private boolean validate(ContactRequest request, StreamObserver<ContactResponse> responseObserver) {
        if (!request.hasContact() || Strings.isNullOrEmpty(request.getContact().getEmail())) {
            Exception exception = new IllegalArgumentException("Contact information is required");
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(exception.getMessage())
                    .withCause(exception)
                    .asRuntimeException());
            return false;
        }
        return true;
    }

}
