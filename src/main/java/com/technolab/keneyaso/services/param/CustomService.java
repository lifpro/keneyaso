package com.technolab.keneyaso.services.param;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URI;


@Slf4j
@Service
public class CustomService {

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(cron = "*/10 * * * * *")
    public void sendMail() {
    log.info("Mail envoyé");
    }
    public String convert() {
        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer your_access_token");
            headers.set("Content-Type", "application/json");
            headers.set("X-RapidAPI-Key", "af828f2779msh56f15518a123867p1dab84jsn14ab2bb6eeb9");
            headers.set("X-RapidAPI-Host", "currency-exchange.p.rapidapi.com");




            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://currency-exchange.p.rapidapi.com/exchange")
                    .queryParam("from", "EUR")
                    .queryParam("to", "USD")
                    .queryParam("q", "1");

            URI uri = builder.build().encode().toUri();

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,  // Change the HttpMethod as per your requirement
                    entity,
                    String.class);
            return response.getBody();

            /*String json = restTemplate.getForObject("", String.class);
            //log.info(json);
            //System.out.println(json);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Medecins>>() {
            }.getType();
            List<Medecins> list = gson.fromJson(json, listType);
            list.forEach(a -> {

            });
            return list;
            return "";*/
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }
}
