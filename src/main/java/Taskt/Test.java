package Taskt;

import Taskt.Services.TaskService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Scanner;
@Configuration
@ComponentScan("Taskt")
@EnableAspectJAutoProxy

public class Test {

    public static void main(String[] args) {
        TaskService service = new TaskService();
        boolean flag = true;
        while (flag){
            Scanner scanner = new Scanner(System.in);
            System.out.println("What u want to do? \n" +
                    "1. Add task \n" +
                    "2. Get all tasks \n"+
                    "3. Delete task \n" +
                    "4. Stop program");
            int actionId = scanner.nextInt();
            switch (actionId){
                case 1:
                    service.addTaskToTable();
                    break;
                case 2:
                    service.getAllTasks();
                    break;
                case 3:
                    service.deleteFromTable();
                    break;
                case 4:
                    flag = false;
                    System.out.println("U've been ended program");
            }
        }
    }
}
