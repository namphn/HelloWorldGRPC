package simple;

import com.codenotfound.grpc.helloworld.HelloWorld;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.codenotfound.grpc.helloworld.Greeting;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc;
import com.codenotfound.grpc.helloworld.Person;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class HelloWorldClient {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(HelloWorldClient.class);

    private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 9090).usePlaintext().build();

        helloWorldServiceBlockingStub =
                HelloWorldServiceGrpc.newBlockingStub(managedChannel);
    }

    public String sayHello(String firstName, String lastName) {
        Person person = Person.newBuilder().setFirstName(firstName)
                .setLastName(lastName).build();
        LOGGER.info("client sending {}", person);

        Greeting greeting =
                helloWorldServiceBlockingStub.sayHello(person);
        LOGGER.info("client received {}", greeting);

        return greeting.getMessage();
    }

    public static void main(String[] args) {
        HelloWorldClient helloWorldClient = new HelloWorldClient();
        helloWorldClient.init();
        System.out.println(helloWorldClient.sayHello("nam","pham hoang"));


    }
}