package ua.epam.provider.timer;

public class Task implements Runnable {
    public void run() {
        System.out.println("task is run");
        new Check().checkAllUsers();
    }
}
