package org.flightsearch.config;

import java.util.List;
import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
public class JacksonConfig extends WebMvcConfigurerAdapter{
	 /**
	   * Make sure dates are serialised in
	   * ISO-8601 format instead as timestamps
	   * with default timezone
	   */
	  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		  for (HttpMessageConverter<?> converter : converters) {
			  if (converter instanceof MappingJackson2HttpMessageConverter) {
				  MappingJackson2HttpMessageConverter jsonMessageConverter  = (MappingJackson2HttpMessageConverter) converter;
				  ObjectMapper objectMapper  = jsonMessageConverter.getObjectMapper();
				  objectMapper.disable(
					SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
				  );
				  objectMapper.setTimeZone(TimeZone.getDefault());
//				  objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
//				  objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
				  break;
			  }
		  }
	  }
	  
	  @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**");
	    }
}