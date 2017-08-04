package com.example.android.reportcard;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Christian on 09.05.2017.
 */

public class ReportCard {

    //Choice of Country

    public enum COUNTRY {
        US,
        EU
    }

    public enum DATE_STYLE {
        US,
        EU
    }


    //enum for only this values
    public enum GRADES {
        A(0), B(1), C(2), D(3), E(4), F(5);
        private int value;

        GRADES(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    //GRADES US
    private final String GRADE_A = "A";
    private final String GRADE_B = "B";
    private final String GRADE_C = "C";
    private final String GRADE_D = "D";
    private final String GRADE_E = "E";
    private final String GRADE_F = "F";
    //GRADES EU
    private final String GRADE_1 = "1";
    private final String GRADE_2 = "2";
    private final String GRADE_3 = "3";
    private final String GRADE_4 = "4";
    private final String GRADE_5 = "5";
    private final String GRADE_6 = "6";
    //date style for two countries
    private final String DATE_PATTERN_EUROPE = "dd/MM/yyyy";
    private final String DATE_PATTERN_US = "MM/dd/yyyy";
    //Data
    private String mStudentName;
    private String mStudentBirthday;
    private String mDateOfIssuing;
    private String mStudentAddress;
    private String mClass;
    private String mStudentPostCode;
    private String mStudentCountry;
    private String mFormTeacherName;
    private String mHighSchool;
    private String datePattern;
    //grades and tasks
    private final String[] grades = new String[6];
    private ArrayList<String> gradesOfTasks = new ArrayList<>();
    private ArrayList<String> tasks = new ArrayList<>();

    /**
     * Init a Report Card Object with country information and standard values
     *
     * @param country   This will set the grade system letters or numbers
     * @param dateStyle This will set the date style from
     *                  american to eu like dd/MM/yyyy and MM/dd/yyyy
     */

    public ReportCard(COUNTRY country, DATE_STYLE dateStyle) {
        //if you initialize the class you could choose the land and the date format
        if (country == COUNTRY.US) {
            this.grades[0] = GRADE_A;
            this.grades[1] = GRADE_B;
            this.grades[2] = GRADE_C;
            this.grades[3] = GRADE_D;
            this.grades[4] = GRADE_E;
            this.grades[5] = GRADE_F;

        } else {
            this.grades[0] = GRADE_1;
            this.grades[1] = GRADE_2;
            this.grades[2] = GRADE_3;
            this.grades[3] = GRADE_4;
            this.grades[4] = GRADE_5;
            this.grades[5] = GRADE_6;
        }

        if (dateStyle == DATE_STYLE.US) {
            this.datePattern = DATE_PATTERN_US;
        } else {
            this.datePattern = DATE_PATTERN_EUROPE;
        }

        //Standards
        this.mStudentName = "No Man";
        setStudentBirthday(12, 12, 2012);
        this.mStudentCountry = "No Mans Land";
        this.mStudentAddress = "No Mans Address";
        this.mStudentPostCode = "1212";
        setDateOfIssuing(17, 12, 2017);
        this.mFormTeacherName = "Mr. Peach Harald";
        this.mHighSchool = "No Mans School";
        this.mClass = "No Mans Class";

        //Standards
        addTaskAndGrade(0, "English", ReportCard.GRADES.A);
        addTaskAndGrade(1, "German", ReportCard.GRADES.C);
        addTaskAndGrade(2, "Maths", ReportCard.GRADES.F);
        addTaskAndGrade(3, "Biology", ReportCard.GRADES.B);
        addTaskAndGrade(4, "Mechanics", ReportCard.GRADES.C);

    }

    public String getStudentName() {
        return mStudentName;
    }

    public void setStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }

    public String getStudentBirthday() {
        return this.mStudentBirthday;
    }


    public void setStudentBirthday(int day, int month, int year) {

        this.mStudentBirthday = getDateFromInteger(day, month, year);

    }

    public String getDateOfIssuing() {
        return mDateOfIssuing;
    }

    public void setDateOfIssuing(int day, int month, int year) {
        this.mDateOfIssuing = getDateFromInteger(day, month, year);
    }

    public String getStudentAdress() {
        return mStudentAddress;
    }

    public void setStudentAdress(String mStudentAddress) {
        this.mStudentAddress = mStudentAddress;
    }

    public String getSchoolClass() {
        return mClass;
    }

    public void setSchoolClass(String mClass) {
        this.mClass = mClass;
    }

    public String getStudentPostCode() {
        return mStudentPostCode;
    }

    public void setStudentPostCode(String mStudentPostCode) {
        this.mStudentPostCode = mStudentPostCode;
    }

    public String getStudentCountry() {
        return mStudentCountry;
    }

    public void setStudentCountry(String mStudentCountry) {
        this.mStudentCountry = mStudentCountry;
    }

    public String getFormTeacherName() {
        return mFormTeacherName;
    }

    public void setFormTeacherName(String mFormTeacherName) {
        this.mFormTeacherName = mFormTeacherName;
    }

    public String getHighSchool() {
        return mHighSchool;
    }

    public void setHighSchool(String mHighSchool) {
        this.mHighSchool = mHighSchool;
    }

    public String getTask(int index) {
        String task;
        if (index < tasks.size() && index >= 0) {

            return this.tasks.get(index);
        } else {
            Log.e("Error", "Index out of bounds");
            task = "Index out of bounds";
        }
        return task;

    }

    public void addTasks(String task, int index) {
        if (index < tasks.size() && index >= 0) {
            this.tasks.add(index, task);
            this.gradesOfTasks.add(index, "A");
        } else {
            Log.e("Error", "Index out of bounds");
        }

    }

    public void deleteTask(int index) {
        if (index < tasks.size() && index >= 0) {
            this.tasks.remove(index);
            this.gradesOfTasks.remove(index);
        } else {
            Log.e("Error", "Index out of bounds");
        }
    }


    public void clearAllTasks() {
        this.tasks.clear();
        this.gradesOfTasks.clear();
    }


    public void addTaskAndGrade(int index, String task, GRADES grade) {
        if (index >= 0) {
            tasks.add(index, task);
            gradesOfTasks.add(index, grades[grade.getValue()]);
        } else {
            Log.e("Error", "Index out of bounds");
        }

    }


    @Override
    public String toString() {
        String reportCard;

        reportCard = "Report Card\n";
        reportCard +=
                String.format("%s%s\n", "Date: ", mDateOfIssuing);
        reportCard +=
                String.format("%s%s\n", "Student: ", mStudentName);
        reportCard +=
                String.format("%s%s\n", "Date of Birth: ", mStudentBirthday);
        reportCard +=
                "\nAddress:\n" + mStudentAddress + "\n" + mStudentPostCode + ", " + mStudentCountry + "\n";
        reportCard +=
                "\n";

        reportCard +=
                String.format("%s%s\n", "Form Teacher: ", mFormTeacherName);
        reportCard +=
                String.format("%s%s\n", "School: ", mHighSchool);
        reportCard +=
                String.format("%s%s\n", "Class: ", mClass);
        reportCard +=
                "\n" + "\n";


        reportCard +=
                String.format("%-40s%s\n", "Task", "Grade");

        reportCard +=
                "-------------------------------------------------------" + "\n";


        for (int i = 0; i <= tasks.size() - 1; i++) {


            reportCard +=
                    String.format("%-42s%s\n", tasks.get(i), gradesOfTasks.get(i));

            reportCard +=
                    "-------------------------------------------------------" + "\n";

        }

        reportCard += "\n\n";


        return reportCard;
    }


    public String getGrade(int index) {
        if (index < gradesOfTasks.size() && index >= 0) {

            return gradesOfTasks.get(index);
        } else {
            return gradesOfTasks.get(0);
        }
    }


    public String getTaskWithGrade(int index) {

        String stringTaskWithGrade = "";

        if (index < tasks.size() && index >= 0) {
            return tasks.get(index) +
                    " ----------------- " + gradesOfTasks.get(index);
        } else {
            stringTaskWithGrade = tasks.get(tasks.size() - 1) +
                    " ----------------- " + gradesOfTasks.get(gradesOfTasks.size() - 1);
            Log.e("Error", "Index out of bounds");
        }

        return stringTaskWithGrade;

    }


    private String getDateFromInteger(int day, int month, int year) {
        String dateString;
        Calendar calendar = new GregorianCalendar();

        if (datePattern.equals(DATE_PATTERN_EUROPE)) {
            //Day
            if (day <= 31 && day > 0) {
                dateString = String.valueOf(day) + "/";
            } else {
                dateString = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "/";
            }


            //Month

            if (month <= 12 && month > 0) {
                dateString += String.valueOf(month) + "/";
            } else {
                dateString += String.valueOf(calendar.get(Calendar.MONTH)) + "/";
            }


            if (year < 9999 && year > 999) {
                dateString += String.valueOf(year);
            } else {
                dateString += String.valueOf(calendar.get(Calendar.YEAR));
            }
        } else {

            //Month

            if (month <= 12 && month > 0) {
                dateString = String.valueOf(month) + "/";
            } else {
                dateString = String.valueOf(calendar.get(Calendar.MONTH)) + "/";
            }


            //Day
            if (day <= 31 && day > 0) {
                dateString += String.valueOf(day) + "/";
            } else {
                dateString += String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "/";
            }

            if (year < 9999 && year > 999) {
                dateString += String.valueOf(year);
            } else {
                dateString += String.valueOf(calendar.get(Calendar.YEAR));
            }
        }

        return dateString;
    }


}

