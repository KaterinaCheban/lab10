package ua.nure.time;

import  java.io.ObjectInputStream;
import  java.net.Socket;

public  class  ClientTCP  {
    public  static  void  main(String  args[])  {
        try  {
//  Створюється  об'єкт  Socket
//  для  з’єднання  з  сервером
            Socket  clientSocket  =  new  Socket("localhost",  1500);
//  Отримаємо  посилання  на  поток,  пов’язаний  із  сокетом
            ObjectInputStream  in  =  new
                    ObjectInputStream(clientSocket.getInputStream());
//  Витягуємо  об'єкт  з  вхідного  потоку
            DateMessage  dateMessage  =
                    (DateMessage)  in.readObject();
//  Виводимо  отримані  дані  на  консоль
            System.out.println(dateMessage.getMessage());
            System.out.println(dateMessage.getDate());
        }  catch  (Exception  e)  {
            e.printStackTrace();
        }
    }
}
