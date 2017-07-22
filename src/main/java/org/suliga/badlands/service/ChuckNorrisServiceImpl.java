package org.suliga.badlands.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChuckNorrisServiceImpl implements ChuckNorrisService {

	@Override
	public ChuckNorrisJoke getRandomJoke() {
		RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.add("user-agent", "Chrome/59.0.3071.115");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String url = "https://api.chucknorris.io/jokes/random";
        ResponseEntity<String> res = rt.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(res);
        ObjectMapper om = new ObjectMapper();
        try {
			ChuckNorrisJoke joke = om.readValue(res.getBody(), ChuckNorrisJoke.class);
			return joke;
		} catch (IOException e) {
			e.printStackTrace();
		}
        return new ChuckNorrisJoke();
	}
}
