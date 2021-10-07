package Storages;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class User {
    public String address;
    public String topic;
    public User(String adr, String topic)
    {
        this.address = adr;
        ManagedChannel channel = ManagedChannelBuilder.forTarget(adr)
                .usePlaintext().build();
        this.topic = topic;
    }
}
