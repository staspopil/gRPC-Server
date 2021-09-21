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
    @Override
    public void subscribe(Service.subscribeRequest request, StreamObserver<Service.subsribeResponse> responseObserver)
    {
        System.out.println("New Connection: " + request+"\n");
        userStorage.addUser(new User(request.getAdress()));
        Service.subsribeResponse.Builder responseBuilder = Service.subsribeResponse.newBuilder();
        Service.subsribeResponse response = responseBuilder.setIsSucces("Succces").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
