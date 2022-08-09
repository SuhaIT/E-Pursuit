package com.jerucloud.epursuit;

import android.annotation.SuppressLint;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class data_job_adds implements Serializable {
     String title,JobCategory,JobSalary,JobCity,JobDescription,ContractPeriod,Workingtime,Gender,Majorr,
    Qualiification,GraduationYear,FiieldfExperience,YearsofExpeirience,rec_own;

    public data_job_adds(String title, String jobCategory, String jobSalary, String jobCity, String jobDescription, String contractPeriod, String workingtime, String gender, String majorr, String qualiification, String graduationYear, String fiieldfExperience, String yearsofExpeirience,String rec_own) {
        this.title = title;
        JobCategory = jobCategory;
        JobSalary = jobSalary;
        JobCity = jobCity;
        JobDescription = jobDescription;
        ContractPeriod = contractPeriod;
        Workingtime = workingtime;
        Gender = gender;
        Majorr = majorr;
        Qualiification = qualiification;
        GraduationYear = graduationYear;
        FiieldfExperience = fiieldfExperience;
        YearsofExpeirience = yearsofExpeirience;
        this.rec_own=rec_own;
    }

    public String getRec_own() {
        return rec_own;
    }

    public void setRec_own(String rec_own) {
        this.rec_own = rec_own;
    }

    public data_job_adds() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobCategory() {
        return JobCategory;
    }

    public void setJobCategory(String jobCategory) {
        JobCategory = jobCategory;
    }

    public String getJobSalary() {
        return JobSalary;
    }

    public void setJobSalary(String jobSalary) {
        JobSalary = jobSalary;
    }

    public String getJobCity() {
        return JobCity;
    }

    public void setJobCity(String jobCity) {
        JobCity = jobCity;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String jobDescription) {
        JobDescription = jobDescription;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getContractPeriod() {

        return ContractPeriod;



        /*
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf
                = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
      /*  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate da1 = LocalDate.parse(ContractPeriod+"00:01:01",dtf);
        LocalDate localDate = LocalDate.now();
        String local=dtf.format(localDate);
        long ndays = datesUntil(local,d1).count();
        return Days.daysBetween(
                new LocalDate(d1.getTime()),
                new LocalDate(d2.getTime())).getDays();
        return ndays+" days left";*/


        // SimpleDateFormat converts the
        // string format to date object


        // Try Block


            // parse method is used to parse
            // the text from a string to
            // produce the date
          /*  LocalDateTime now = LocalDateTime.now();
            Date d1 = sdf.parse(String.valueOf(now));
            Date d2 = sdf.parse(ContractPeriod+"00:01:01");

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds

            System.out.print(
                    "Difference "
                            + "between two dates is: ");


            return difference_In_Days + " ";*/

    }
/*
        final String gameDate = date1+"T09:00:00Z";
        final SimpleDateFormat apiFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        apiFormat.setTimeZone(TimeZone.getTimeZone("CET"));
        final Date dateOfGame = apiFormat.parse(gameDate);
        final long millis = dateOfGame.getTime() - System.currentTimeMillis();
        System.out.println(dateOfGame.getTime() - System.currentTimeMillis());

        @SuppressLint("DefaultLocale") final String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        if (TimeUnit.MILLISECONDS.toHours(millis)<=48){
            if (TimeUnit.MILLISECONDS.toHours(millis)==48){
                return "2 days left";
            }else{
                return "1 days left";
            }

        }else if (TimeUnit.MILLISECONDS.toHours(millis)<=72){
            return "3 days left";
        } else if (TimeUnit.MILLISECONDS.toHours(millis)<=96){
            return "4 days left";
        } else if (TimeUnit.MILLISECONDS.toHours(millis)<=120){
            return "5 days left";
        }else if (TimeUnit.MILLISECONDS.toHours(millis)<=144){
            return "6 days left";
        }else if (TimeUnit.MILLISECONDS.toHours(millis)<=168){
            return "7 days left";
        }else {
            return "more than one week left";
        }

*/



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setContractPeriod(String contractPeriod) {
        if (contractPeriod.contains("-")){
        String[] parts = ContractPeriod.split("-");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        int foo1 = Integer.parseInt(part1);
        int foo2 = Integer.parseInt(part2);
        int foo3 = Integer.parseInt(part3);

        final LocalDate target = LocalDate.of(foo1, foo2, foo3);
        final LocalDate today = LocalDate.now();
        if(today.until(target, ChronoUnit.DAYS)==1){
            ContractPeriod= "1 days left";
        }else if(today.until(target, ChronoUnit.DAYS)==2){
            ContractPeriod= "2 days left";
        }else if(today.until(target, ChronoUnit.DAYS)==3){
            ContractPeriod= "3 days left";
        }else if(today.until(target, ChronoUnit.DAYS)==4){
            ContractPeriod= "4 days left";
        }else if(today.until(target, ChronoUnit.DAYS)==5){
            ContractPeriod= "5 days left";
        }else if(today.until(target, ChronoUnit.DAYS)==6){
            ContractPeriod= "6 days left";
        }else if(today.until(target, ChronoUnit.DAYS)==7){
            ContractPeriod= "one week left";
        }else
            ContractPeriod="more than 1 week left ";}


    }

    public String getWorkingtime() {
        return Workingtime;
    }

    public void setWorkingtime(String workingtime) {
        Workingtime = workingtime;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMajorr() {
        return Majorr;
    }

    public void setMajorr(String majorr) {
        Majorr = majorr;
    }

    public String getQualiification() {
        return Qualiification;
    }

    public void setQualiification(String qualiification) {
        Qualiification = qualiification;
    }

    public String getGraduationYear() {
        return GraduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        GraduationYear = graduationYear;
    }

    public String getFiieldfExperience() {
        return FiieldfExperience;
    }

    public void setFiieldfExperience(String fiieldfExperience) {
        FiieldfExperience = fiieldfExperience;
    }

    public String getYearsofExpeirience() {
        return YearsofExpeirience;
    }

    public void setYearsofExpeirience(String yearsofExpeirience) {
        YearsofExpeirience = yearsofExpeirience;
    }
}
