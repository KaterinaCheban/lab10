package ua.nure.time;

import  java.io.ObjectOutputStream;
import  java.net.ServerSocket;
import  java.net.Socket;
import  java.util.Calendar;
/**
 *  Клас  серверу  (здійснюється  в  окремому  процесі)
 */
public  class  ServerTCP  extends  Thread  {
    //  Оголошується  посилання
//  на  об'єкт  -  сокет  серверу
    ServerSocket  serverSocket  =  null;
    /**
     *  Конструктор  за  замовчуваннем
     */
    public  ServerTCP()  {
        try  {
//  Створюється  об'єкт  ServerSocket,  який  получає
//  запит  кліента  на  порт  1500
            serverSocket  =  new  ServerSocket(1500);
            System.out.println("Starting  the  server  ");
//  Запускаємо  процес
            start();
        }  catch  (Exception  e)  {
            e.printStackTrace();
        }
    }
    /**
     *  Запуск  процесу
     */
    public  void  run()  {
        try  {
            while  (true)  {
//  Очікування  запитів  з'єднання  від  клієнтів
                Socket  clientSocket  =  serverSocket.accept();
                System.out.println("Connection  accepted  from  "
                        +  clientSocket.getInetAddress().getHostAddress());
//  Отримання  вихідного  потоку,
//  пов'язаного  з  об'єктом  Socket
                ObjectOutputStream  out  =
                        new  ObjectOutputStream(
                                clientSocket.getOutputStream());
//  Створення  об'єкту  для  передачі  клієнтам
                DateMessage  dateMessage  =  new  DateMessage(
                        Calendar.getInstance().getTime(),
                        "Поточна  дата/час  на  сервері");
//  Запис  об'єкту  у  вихідний  потік
                out.writeObject(dateMessage);
                out.close();
            }
        }  catch  (Exception  e)  {
            e.printStackTrace();
        }
    }

    public  static  void  main(String  args[])  {
//  Запуск  серверу
        new  ServerTCP();
    }

}
