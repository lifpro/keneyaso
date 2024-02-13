package com.technolab.keneyaso;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class KeneyasoRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        /*System.gc();
        String myBloc= """ 
        Un peuple
        Un but
        Une foie
        """;
        System.out.println(myBloc);
        Process ps=Runtime.getRuntime().exec("ls -l");
        String line="";
        BufferedReader reader=new BufferedReader(new InputStreamReader(ps.getInputStream()));
        while ((line=reader.readLine())!=null){
            System.out.println(line);
        }
        ps.waitFor();
        reader.close();
        // DATETIMES
        /*Optional<LocalDate> odate=Optional.of(LocalDate.now());
        if(odate.isPresent()){
            LocalDate mydate=odate.get();
            System.out.println("LocalDate="+mydate.getDayOfWeek());
        }else{
            System.out.println("La date est vide");
        }

        //ZonedDateTime zdate=ZonedDateTime.now();
        //System.out.println("ZonedDateTime="+zdate);


        //List<Integer> nombres= Arrays.asList(1,2,3,10,5,6,7,8,9,4);
        //nombres.stream().filter(n->n%2==0).sorted().forEach(System.out::println);
        /*for (Integer i:nombres){
            System.out.println(i);
        }*/
    }
}

interface Calucl{
    int calculate();
}