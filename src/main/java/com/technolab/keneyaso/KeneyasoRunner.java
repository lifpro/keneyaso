package com.technolab.keneyaso;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class KeneyasoRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        List<Integer> nombres= Arrays.asList(1,2,3,10,5,6,7,8,9,4);
        nombres.stream().filter(n->n%2==0).sorted().forEach(System.out::println);
        /*for (Integer i:nombres){
            System.out.println(i);
        }*/
    }
}

interface Calucl{
    int calculate();
}