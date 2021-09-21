package Storages;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class User {
    public String address;
    public User(String adr)
    {
        this.address = adr;
        ManagedChannel channel = ManagedChannelBuilder.forTarget(adr)
                .usePlaintext().build();
    }
}
