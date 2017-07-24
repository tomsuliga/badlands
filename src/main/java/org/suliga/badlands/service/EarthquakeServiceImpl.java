package org.suliga.badlands.service;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.suliga.badlands.service.EarthquakeEvent.Features;
import org.codehaus.jackson.map.ObjectMapper;

@Service
public class EarthquakeServiceImpl implements EarthquakeService {
	private static final Logger logger = LoggerFactory.getLogger(EarthquakeServiceImpl.class);
	
	@Override
	public List<String> doit() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson";
		String result = restTemplate.getForObject(url, String.class);
		logger.debug("result=" + result);
		ObjectMapper om = new ObjectMapper();
		//ObjectMapper om = new ObjectMapper().configure(Feature.ALLOW_COMMENTS, true); 
		List<String> list = new ArrayList<>();
		try {
			EarthquakeEvent event = om.readValue(result, EarthquakeEvent.class);
			logger.debug("event=" + event);
			Features[] features = event.getFeatures();
			Arrays.sort(features);
			Arrays.asList(features).stream().forEach(f -> {
    			StringBuilder sb = new StringBuilder();
    			sb.append("Magnitude: " + oneDecimalPlace(f.getProperties().get("mag")));
    			sb.append("<br />");
     			sb.append("Location: " + f.getProperties().get("place"));
    			sb.append("<br />");
     			sb.append("Date: " + eqDate(f.getProperties().get("time")));
    			sb.append("<br /><br />");
    			list.add(sb.toString());
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private String oneDecimalPlace(String string) {
		if (string.contains("."))
			return string;
		return string + ".0";
	}
	
	private String eqDate(String longDate) {
		Instant date = Instant.ofEpochMilli(Long.parseLong(longDate));
		ZonedDateTime z = ZonedDateTime.ofInstant(date, ZoneId.of("UTC+0"));
		return DateTimeFormatter.ofPattern("MMM dd hh:mm a").format(z);
	}
}

