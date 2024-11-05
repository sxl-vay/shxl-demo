package top.boking.webtest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTemplateDemo {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8080/t/a";
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        HttpStatusCode statusCode = forEntity.getStatusCode();
        System.out.println("statusCode = " + statusCode);
        String body = forEntity.getBody();
        System.out.println("body = " + body);
    }
}
