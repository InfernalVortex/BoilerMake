package com.wizardpoker.schedulrboilermake;

import java.util.ArrayList;

/**
 * Created by Jonathan on 10/17/2015.
 */
public class Schedule {
    private boolean underPressure;
    private boolean breakDuring;
    private boolean feelsWell;
    private int breakFrequency;
    private int breakDuration;
    private ArrayList<String[]> events = new ArrayList<>(); // edited events
    private ArrayList<String[]> schedule = new ArrayList<>(); // just user entered events

    public Schedule(boolean underPressure, boolean breakDuring, int breakFrequency,
                    boolean feelsWell) {
        this.underPressure = underPressure;
        this.breakDuring = breakDuring;
        this.breakFrequency = breakFrequency;
        this.feelsWell = feelsWell;
        if (this.breakFrequency == 0)
            breakDuration = 10;
        else if (this.breakFrequency == 1)
            breakDuration = 20;
        else breakDuration = 30;
    }

    public void addEvent(String title, int time, int importance) {
        String sTime = time + "";
        String sImportance = importance + "";
        String[] event = {title, sTime, sImportance};

        schedule.add(event);
    }

    public void delEvent(String title, int time, int importance) {
        String sTime = time + "";
        String sImportance = importance + "";
        String[] event = {title, sTime, sImportance};

        schedule.remove(event);
    }

    public void sortEvents(boolean underPressure) {
        if (underPressure == false) {
            boolean sorting = true;
            while (sorting) {
                for (int i = 0; i < events.size(); i++) {
                    String[] temp;
                    String[] a = events.get(i);
                    String[] b = events.get(i+1);
                    if (Integer.parseInt(a[2]) < Integer.parseInt(b[2])) {
                        temp = a;
                        a = b;
                        b = temp;
                        events.set(i,a);
                        events.set(i,b);
                        break;
                    }
                }
            }
        }
        else {
            boolean sorting = true;
            while (sorting) {
                for (int i = 0; i < events.size(); i++) {
                    String[] temp;
                    String[] a = events.get(i);
                    String[] b = events.get(i+1);
                    if (Integer.parseInt(a[2]) > Integer.parseInt(b[2])) {
                        temp = a;
                        a = b;
                        b = temp;
                        events.set(i,a);
                        events.set(i,b);
                        break;
                    }
                }
            }
        }

    }

    public int detTotalTime() {
        int total = 0;
        for (int i = 0; i < events.size(); i++) {
            String[] temp = events.get(i);
            total += Integer.parseInt(temp[1]);
        }
        return total;
    }

    /*
    Splits events up into breakDuration intervals to prepare for adding breaks;
     */
    public void splitEvents(int breakDuration) {
        ArrayList<String[]> temp = new ArrayList<>();
        int workTime;
        if (breakDuration == 10)
            workTime = 30;
        else if (breakDuration == 20)
            workTime = 60;
        else workTime = 90;

        for (int i = 0; i < events.size(); i++) {
            String[] a = events.get(i);
            int time = Integer.parseInt(a[1]);
            int intervals = time / workTime;
            while (time > workTime) {
                a[1] = workTime + "";
                temp.add(a);
                time -= workTime;
            }
            if (time > 0) {
                a[1] = time + "";
                temp.add(a);
            }
        }
        events = temp;
    }

    public void addBreaks(boolean breakDuring, int breakDuration) {
        if (breakDuring) {
            for (int i = 0; i < events.size(); i += 2) {
                String[] e = events.get(i);
                String[] a = {"Break", breakDuration + "", e[2]};
                events.add(i+1,a);
            }
        }
        else {
            for (int i = 0; i < events.size(); i += 2) {
                int a = 1;
                String[] ei = events.get(i);
                for (int j = i+1; j < events.size(); j++) {
                    String[] ej = events.get(j);
                    if (Integer.parseInt(ej[2]) == Integer.parseInt(ei[2]))
                        a++;
                    i = j;
                }
                String[] b = {"Break", breakDuration * a + "", ei[2]};
                events.add(i+1, b);
            }
        }
    }

    public void delayStart(boolean feelsWell, int breakDuration) {
        if (!feelsWell) {
            String[] a = {"Break", breakDuration + "", 0 + ""};
            events.add(0,a);
        }
    }

    public ArrayList<String[]> refreshSchedule() {
        events = schedule;
        sortEvents(underPressure);
        splitEvents(breakDuration);
        addBreaks(breakDuring, breakDuration);
        return events;
    }

}
