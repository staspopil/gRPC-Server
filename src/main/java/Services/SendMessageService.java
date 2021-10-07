package Services;

import Storages.Message;
import Storages.MessageStorage;
import Storages.User;
import Storages.UserStorage;
import com.example.grpc.Service;
import com.example.grpc.messageReceiverGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class SendMessageService {
    UserStorage uStorage = UserStorage.getInstance();
    MessageStorage messageStorage = MessageStorage.getInstance();

    public void broadcastMessage(Message mes)
    {
        Service.newResponse Broadcast_response = null;
        for (User a : uStorage.users)
        {
            if (a.topic.equals(mes.getTopic()))
            {
                ManagedChannel channel = ManagedChannelBuilder.forTarget(a.address)
                        .usePlaintext().build();
                messageReceiverGrpc.messageReceiverBlockingStub stub = messageReceiverGrpc.newBlockingStub(channel);
                Service.receivedMessage.Builder requestBuilder = Service.receivedMessage.newBuilder();
                Service.receivedMessage Broadcast_request = requestBuilder
                        .setName(mes.getSourcename())
                        .setBody(mes.getMessagebody())
                        .build();
                Broadcast_response = stub.receive(Broadcast_request);
                if (Broadcast_response.getIsSucces().equals("Success"))
                    System.out.println("Message was send to the user: " + a.address+"\n");
            }
        }
    }

    public void sendMessagesFromStorage(User user)
    {
        Service.newResponse Broadcast_response = null;
        for (Message mes : messageStorage.messages)
        {
            if(user.topic.equals(mes.getTopic())) {
                ManagedChannel channel = ManagedChannelBuilder.forTarget(user.address)
                        .usePlaintext().build();
                messageReceiverGrpc.messageReceiverBlockingStub stub = messageReceiverGrpc.newBlockingStub(channel);
                Service.receivedMessage.Builder requestBuilder = Service.receivedMessage.newBuilder();
                Service.receivedMessage Broadcast_request = requestBuilder
                        .setName(mes.getSourcename())
                        .setBody(mes.getMessagebody())
                        .build();
                Broadcast_response = stub.receive(Broadcast_request);
                if (Broadcast_response.getIsSucces().equals("Success")) ;
            }
        }
    }



}
