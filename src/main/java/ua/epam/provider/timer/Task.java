package ua.epam.provider.timer;

public class Task implements Runnable {
    public void run() {
        new Check().checkAllUsers();
        System.out.println("task is run");
    }

}
