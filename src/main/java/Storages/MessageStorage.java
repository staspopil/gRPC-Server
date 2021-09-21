package Storages;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageStorage {
    private static volatile MessageStorage instance;

    private ConcurrentLinkedQueue<Message> messages;

    public MessageStorage() {
        messages = new ConcurrentLinkedQueue<Message>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message getMessage() {
        return messages.poll();
    }

    public boolean isEmpty() {
        return messages.isEmpty();
    }

    public static MessageStorage getInstance() {
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
        MessageStorage result = instance;
        if (result != null) {
            return result;
        }
        synchronized (MessageStorage.class) {
            if (instance == null) {
                instance = new MessageStorage();
            }
            return instance;
        }
    }
}
