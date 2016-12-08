package nl.ruudclaassen.movie_list;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import nl.ruudclaassen.movie_list.data.external.tmdb.DiscoverResource;
import nl.ruudclaassen.movie_list.data.external.tmdb.MovieResource;

// TODO: How to create a connection with the h2 database? 

@EnableAutoConfiguration
@ComponentScan
public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	@Value("${tmdb.client.address}")
	private String tmdbClientAddress;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalEntityManagerFactoryBean();
		localEntityManagerFactoryBean.setPersistenceUnitName("PersistenceUnit");

		return localEntityManagerFactoryBean;
	}

	@Bean
	public FormattingConversionServiceFactoryBean formattingService() {
		FormattingConversionServiceFactoryBean bean = new FormattingConversionServiceFactoryBean();
		Set<Formatter> format = new HashSet<Formatter>();
		bean.setFormatters(format);

		return bean;
	}
	
	@Bean
	public JacksonJaxbJsonProvider jacksonJaxbJsonProvider(){
		return new JacksonJaxbJsonProvider();
	}
	

	@Bean(destroyMethod = "stop")
	public Server h2WebServer() throws SQLException {
		return Server.createWebServer("-webAllowOthers", "-webPort", "8082").start();
	}

	@Bean
	public DiscoverResource discoverResource() {
		JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
		bean.setServiceClass(DiscoverResource.class);
		bean.setAddress(tmdbClientAddress);
		bean.setProvider(jacksonJaxbJsonProvider());
		return bean.create(DiscoverResource.class);
	}

	@Bean
	public MovieResource movieResource() {
		JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
		bean.setServiceClass(MovieResource.class);
		bean.setAddress(tmdbClientAddress);
		bean.setProvider(jacksonJaxbJsonProvider());
		return bean.create(MovieResource.class);
	}
	
}
