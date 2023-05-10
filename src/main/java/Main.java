import Toys.Toy;
import Toys.ToysFactory;
import Toys.ToysType;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        Map<Integer,Toy> map =new HashMap<>();
        System.out.println("Добро пожаловать в магазин игрушек!");
        do {
            System.out.println("""
                    Выберите действие:\s
                    1)Посмотреть список игрушек.
                    2)Добавить игрушку.
                    3)Изменить игрушку.
                    4)Разыграть игрушку.
                    0)Выйти.""");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (map.keySet().isEmpty()) {
                        System.out.println("В магазине нет игрушек! Добавьте их, и попробуйте снова!");
                    } else {
                        for (Map.Entry<Integer,Toy> entry:map.entrySet()){
                            System.out.println(entry.getValue());
                        }
                    }
                    break;
                case 2:
                    Toy toy;
                    ToysFactory toysFactory = new ToysFactory();
                    Scanner scanner2=new Scanner(System.in);
                    System.out.println("Выберите тип игрушки, имя, шанс выпадения через запятую.\n" +
                            "Например: PLAIN, Боинг, 0.25." +
                            "\nДоступные типы создания: " + Arrays.asList(ToysType.values()));
                    String[] strings=scanner2.nextLine().split(", ");
                    toy=toysFactory.createToy(ToysType.valueOf(strings[0]),strings[1],Float.parseFloat(strings[2]));
                    map.put(toy.getId(),toy);
                    System.out.println("Игрушка успешно добавлена!");
                    break;
                case 3:
                    Scanner scanner3=new Scanner(System.in);
                    System.out.println("Введите id игрушки: ");
                    int id=scanner3.nextInt();
                    System.out.println("Введите новое имя и шанс выпадения: \nНапример: Porsche 911, 0.5.");
                    Scanner scanner3_1=new Scanner(System.in);
                    String[] strings3=scanner3_1.nextLine().split(", ");
                    map.get(id).change(strings3[0],Float.parseFloat(strings3[1]));
                    System.out.println("Игрушка была изменена!");
                    break;
                case 4:
                    //Не совсем понимаю почему но файл с разыгранной игрушкой
                    // появляется лишь после выхода из программы 0.Выйти
                    if(map.keySet().isEmpty()){
                        System.out.println("Игрушек нету, разыгрывать нечего!");
                    }else{
                        Toy toyRaffle=map.get(0);
                        for (Map.Entry<Integer,Toy> entry:map.entrySet()){
                            if(entry.getValue().getPercent()>toyRaffle.getPercent()){
                                toyRaffle=entry.getValue();
                            }
                        }
                        int id_raffle = toyRaffle.getId();
                        FileOutputStream out=new FileOutputStream("Разыгранные игрушки");
                        ObjectOutputStream objectOutputStream=new ObjectOutputStream(out);
                        objectOutputStream.writeObject(toyRaffle.toString());
                        objectOutputStream.close();
                        toyRaffle.remove();
                        map.remove(id_raffle);
                    }
                    break;
                case 0:
                    System.out.println("До свидания!");
                    flag = false;
                    break;
            }
        } while (flag);
    }
}
