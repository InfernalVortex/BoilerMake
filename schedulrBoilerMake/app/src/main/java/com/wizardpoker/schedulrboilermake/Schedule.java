package com.wizardpoker.schedulrboilermake;

import java.util.ArrayList;

/**
 * Created by Jonathan on 10/17/2015.
 */
public class Schedule {
    private boolean underPressure;
    private boolean breakDuring;
    private int breakFrequency;
    private ArrayList<String[]> events = new ArrayList<>();

    public Schedule(boolean underPressure, boolean breakDuring, int breakFrequency) {
        this.underPressure = underPressure;
        this.breakDuring = breakDuring;
        this.breakFrequency = breakFrequency;
    }

    public void addEvent(String title, int time, int importance) {
        String sTime = time + "";
        String sImportance = importance + "";
        String[] event = {title, sTime, sImportance};

        events.add(event);
    }

    public void delEvent(String title, int time, int importance) {
        String sTime = time + "";
        String sImportance = importance + "";
        String[] event = {title, sTime, sImportance};

        events.remove(event);
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

    public ArrayList<String[]> refreshSchedule() {
        sortEvents(underPressure);
        return events;
    }

}
