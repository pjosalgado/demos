Build without the Maven plugin: 
```
protoc --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java-1.39.0-osx-x86_64 -I=. --java_out=build/ --grpc-java_out=build/ proto/HelloService.proto proto/ContactService.proto
```

List available services:
```
grpc_cli ls localhost:8080
```

Test: 
```
grpcurl --plaintext -d '{"firstName": "Paulo", "lastName": "Salgado"}' localhost:8080 tech.paulosalgado.grpc.HelloService/hello
```