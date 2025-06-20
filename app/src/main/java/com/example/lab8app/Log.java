package com.example.lab8app;
import java.util.ArrayList;
import java.util.List;

public class Log {
    private static final List<String> taskLogs = new ArrayList<>();
    public static void logtask(String message) {
        taskLogs.add(message);
    }
    public static List<String> gettasklogs() {
        return new ArrayList<>(taskLogs);
    }
    public static void cleartasklogs() {
        taskLogs.clear();
    }
}

