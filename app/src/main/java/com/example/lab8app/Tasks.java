package com.example.lab8app;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private static int tasknow = 1;
    public static void settask(int tasknumber) {
        tasknow = tasknumber;
        Log.logtask("Выбрана задача #" + tasknumber);
    }
    public static List<Integer> run(int start, int end) {
        Log.logtask("Начат поиск от " + start + " до " + end);
        List<Integer> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            switch (tasknow) {
                case 1:
                    if (zuckerm(i)) result.add(i);
                    break;
                case 2:
                    if (niven(i)) result.add(i);
                    break;
                case 3:
                    if (lucky(i)) result.add(i);
                    break;
                case 4:
                    if (kaprekar(i)) result.add(i);
                    break;
            }
        }
        if (result.isEmpty()) {
            Log.logtask("Подходящие числа не найдены");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int num : result) {
                sb.append(num).append(" ");
            }
            Log.logtask("Найденные числа: " + sb.toString().trim());
        }
        return result;
    }
    private static boolean zuckerm(int n) {
        if (n == 0) return false;
        int product = 1, temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            if (digit == 0) return false;
            product *= digit;
            temp /= 10;
        }
        return n % product == 0;
    }
    private static boolean niven(int n) {
        if (n == 0) return false;
        int sum = 0, temp = n;
        while (temp > 0) {
            sum += temp % 10;
            temp /= 10;
        }
        return sum != 0 && n % sum == 0;
    }
    private static boolean lucky(int n) {
        List<Integer> seen = new ArrayList<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            int sum = 0;
            while (n > 0) {
                int d = n % 10;
                sum += d * d;
                n /= 10;
            }
            n = sum;
        }
        return n == 1;
    }
    private static boolean kaprekar(int n) {
        if (n == 1) return true;
        long sq = (long) n * n;
        String s = Long.toString(sq);
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);

            int l = Integer.parseInt(left);
            int r = Integer.parseInt(right);
            if (r == 0) continue;
            if (l + r == n) return true;
        }
        return false;
    }
}

