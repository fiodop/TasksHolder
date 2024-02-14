package Taskt.Services;

import Taskt.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaskService {
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.file.xml")
            .addAnnotatedClass(Task.class)
            .buildSessionFactory();
    Session session = null;
    Scanner scanner = new Scanner(System.in);

    public void addTaskToTable() {
        System.out.println("How much tasks u want add?");
        int tasksCnt = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < tasksCnt; i++) {
            //Collecting information about task
            System.out.println("Write your task:");
            String taskName = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Write task's priority:");
            int taskPriority = scanner.nextInt();

            Date date = new Date();
            Task taskToTable = new Task(taskName, date, 0, taskPriority);
            try {
                //Creating session
                session = factory.getCurrentSession();
                session.beginTransaction();
                session.save(taskToTable);
                session.getTransaction().commit();
            } catch (Exception e){
                session.close();
                factory.close();
            }

        }
    }

    public void getAllTasks() {
        try {
            List<Task> tasks = null;
            //Creating session
            session = factory.getCurrentSession();
            session.beginTransaction();
            tasks = session.createQuery("FROM Task").getResultList();

            for (Task e : tasks) {
                System.out.println(e);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.close();
            factory.close();
        }
    }

    public void deleteFromTable() {
        List<Task> deletedTasks = new ArrayList<>();
        System.out.println("How much rows u want to delete?");
        int rowsCnt = scanner.nextInt();
        scanner.nextLine();
        try {
            for (int i = 0; i < rowsCnt; i++) {
                System.out.println("Write task id, which u want to delete");
                int taskId = scanner.nextInt();

                session = factory.getCurrentSession();
                session.beginTransaction();
                Task taskToDelete = session.get(Task.class, taskId);
                session.delete(taskToDelete);
                System.out.println(taskToDelete + " has been deleted");

                session.getTransaction().commit();
            }
        } catch (Exception e) {
            session.close();
            factory.close();
        }
    }
}
