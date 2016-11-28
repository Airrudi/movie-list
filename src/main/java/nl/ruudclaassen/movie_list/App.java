package nl.ruudclaassen.movie_list;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import nl.ruudclaassen.movie_list.web.formatters.GenreFormatter;

// TODO: How to create a connection with the h2 database? 

@EnableAutoConfiguration
@ComponentScan
public class App {
	public static void main(String[] args){
		SpringApplication.run(App.class, args);
	}
	
	@Autowired
	private GenreFormatter genreFormatter;

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactoryBean(){
		LocalEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalEntityManagerFactoryBean();
		localEntityManagerFactoryBean.setPersistenceUnitName("PersistenceUnit");

		return localEntityManagerFactoryBean;
	}
	
	@Bean
	public FormattingConversionServiceFactoryBean formattingService(){
		FormattingConversionServiceFactoryBean bean = new FormattingConversionServiceFactoryBean();
		Set<Formatter> format = new HashSet<Formatter>();
		format.add(genreFormatter);
		bean.setFormatters(format);
		
		return bean;
	}	
	
	@Bean(destroyMethod="stop")
	public Server h2WebServer() throws SQLException {
		return Server.createWebServer("-webAllowOthers", "-webPort","8082").start();
	}
}
