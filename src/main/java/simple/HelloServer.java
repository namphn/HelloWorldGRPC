package simple;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class HelloServer {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("startting GRPC server");
        Server server = ServerBuilder.forPort(9090).addService(new HelloWorldServiceImpl()).build();

        server.start();
        System.out.println("server started at " + server.getPort());
        server.awaitTermination();
    }
}
