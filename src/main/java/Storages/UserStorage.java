package Storages;

import java.util.concurrent.ConcurrentLinkedQueue;

public class UserStorage {
    private static volatile UserStorage instance;
    public ConcurrentLinkedQueue<User> users;

    public UserStorage()
    {
        users = new ConcurrentLinkedQueue<User>();
    }

    public void addUser(User user)
    {
        users.add(user);
    }

    public User getUser()
    {
        return users.peek();
    }

    public boolean isEmpty()
    {
        return users.isEmpty();
    }

    public static UserStorage getInstance() {
        // Техника, которую мы здесь применяем называется «блокировка с двойной
        // проверкой» (Double-Checked Locking). Она применяется, чтобы
        // предотвратить создание нескольких объектов-одиночек, если метод будет
        // вызван из нескольких потоков одновременно.
        //
        // Хотя переменная `result` вполне оправданно кажется здесь лишней, она
        // помогает избежать подводных камней реализации DCL в Java.
        //
        // Больше об этой проблеме можно почитать здесь:
        // https://refactoring.guru/ru/java-dcl-issue
        UserStorage result = instance;
        if (result != null) {
            return result;
        }
        synchronized (UserStorage.class) {
            if (instance == null) {
                instance = new UserStorage();
            }
            return instance;
        }
    }
}
