package app;

import entity.UsersAddress;
import exceptions.UserAddressException;
import org.springframework.context.ApplicationContext;
import services.JSONUserService;
import services.iterfaces.UserService;
import util.json.AppContext;
import util.json.Help;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = AppContext.getContext();

        UserService service = context.getBean(JSONUserService.class);

        startUserInterface(service);

    }

    private static void startUserInterface(UserService service) {
        boolean flag = true;

        System.out.println(">>>>>>> Старт программы >>>>>>>");
        System.out.println("Для вывода справки введите help");
        Scanner sc = new Scanner(System.in);

        while (flag) {
            System.out.print("Введите команду: ");
            String[] command = sc.nextLine().split(" ");

            switch (command[0]) {
                case "help":
                    Help.printHelp();
                    break;
                case "users":
                    try {
                        service.get().forEach(System.out::println);
                    } catch (UserAddressException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "user":
                    try {
                        System.out.println(service.get(Integer.valueOf(command[1])));
                    } catch (UserAddressException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete": {
                    try {
                        service.delete(Integer.valueOf(command[1]));
                    } catch (UserAddressException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case "update":
                    try {
                        UsersAddress user =
                                new UsersAddress(Integer.valueOf(command[1])
                                        , command[2]
                                        , command[3]
                                        , command[4]
                                        , command[5]
                                        , command[6]);
                        service.update(user);
                    } catch (UserAddressException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "insert":
                    try {
                        UsersAddress user =
                                new UsersAddress(Integer.valueOf(command[1])
                                        , command[2]
                                        , command[3]
                                        , command[4]
                                        , command[5]
                                        , command[6]);
                        if(!service.insert(user)) throw new UserAddressException("User with current id alredy exist");
                    } catch (UserAddressException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    flag = false;
                    sc.close();
                    System.out.println(">>>Спасибо что воспользовались нашим приложением");
                    break;
                default:
                    System.out.println("Такой команды нет");
                    break;
            }
        }
    }
}
