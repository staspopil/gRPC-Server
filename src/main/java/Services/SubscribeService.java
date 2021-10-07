package Services;

import Storages.Message;
import Storages.MessageStorage;
import Storages.User;
import Storages.UserStorage;
import com.example.grpc.Service;
import com.example.grpc.subscribeServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SubscribeService extends subscribeServiceGrpc.subscribeServiceImplBase {
    UserStorage userStorage = UserStorage.getInstance();
    MessageStorage messageStorage = MessageStorage.getInstance();
    @Override
    public void subscribe(Service.subscribeRequest request, StreamObserver<Service.subsribeResponse> responseObserver)
    {
        int i = 1;
        SendMessageService sendMessageService = new SendMessageService();
        System.out.println("New Connection: " + request+"\n");
        userStorage.addUser(new User(request.getAdress() , request.getTopic()));
        Service.subsribeResponse.Builder responseBuilder = Service.subsribeResponse.newBuilder();

        for (Message mes : messageStorage.messages)
        {
            if (mes.getTopic().equals(request.getTopic()))
            {
                responseBuilder.addMessage(Service.receivedMessage.newBuilder()
                        .setName(mes.getSourcename())
                        .setBody(mes.getMessagebody()).build());
            }
        }

        Service.subsribeResponse response = responseBuilder.setIsSucces("Succces").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}
