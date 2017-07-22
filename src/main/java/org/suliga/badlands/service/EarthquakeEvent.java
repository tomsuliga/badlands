package org.suliga.badlands.service;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EarthquakeEvent {
	private String type;
	private Map<String,String> metadata;
	private Features[] features;
	private Double[] bbox;

	public EarthquakeEvent() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public Features[] getFeatures() {
		return features;
	}

	public void setFeatures(Features[] features) {
		this.features = features;
	}

	public Double[] getBbox() {
		return bbox;
	}

	public void setBbox(Double[] bbox) {
		this.bbox = bbox;
	}
	
	@Override
	public String toString() {
		return "type=" + type + ", metadata=" + metadata;
	}

	public static class Features implements Comparable<Features> {
		private String type;
		private Map<String,String> properties;
		private Map<String,Object> geometry;
		private String id;
		
		public Features() {}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Map<String, String> getProperties() {
			return properties;
		}

		public void setProperties(Map<String, String> properties) {
			this.properties = properties;
		}

		public Map<String, Object> getGeometry() {
			return geometry;
		}

		public void setGeometry(Map<String, Object> geometry) {
			this.geometry = geometry;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Override
		public int compareTo(Features other) {
			if (other == null)
				return 0;
			String s1 = properties.get("mag");
			String s2 = other.getProperties().get("mag");
			if (s1 == null || s2 == null)
				return 0;
			double d1 = Double.parseDouble(s1);
			double d2 = Double.parseDouble(s2);
			int i1 = (int) (d1 * 100);
			int i2 = (int) (d2 * 100);
			return i2 - i1; // needed for descending order
		}
	}
}

