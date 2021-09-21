package Services;

import Storages.Message;
import Storages.MessageStorage;
import Storages.User;
import Storages.UserStorage;
import com.example.grpc.Service;
import com.example.grpc.messageReceiverGrpc;
import com.example.grpc.subscribeServiceGrpc;
import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

import static java.lang.System.in;

public class ReceiveMessageSerivice extends messageReceiverGrpc.messageReceiverImplBase
{
    MessageStorage messageStorage = MessageStorage.getInstance();
    SendMessageService sendMessageService = new SendMessageService();
    @Override
    public void receive(Service.receivedMessage request, StreamObserver<Service.newResponse> responseObserver)
    {
        messageStorage.addMessage(new Message(request.getName(), request.getBody()));
        Service.newResponse.Builder responseBuilder = Service.newResponse.newBuilder();
        Service.newResponse response = responseBuilder.setIsSucces("Succces").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        sendMessageService.broadcastMessage(messageStorage.getMessage());
    }


}
