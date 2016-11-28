package nl.ruudclaassen.movie_list;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import nl.ruudclaassen.movie_list.web.formatters.GenreFormatter;

// TODO: How to create a connection with the h2 database? 

@EnableAutoConfiguration
@ComponentScan
@SpringBootConfiguration
public class App extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	@Lazy
	private GenreFormatter genreFormatter;

	@Autowired
	private DataSource dataSource;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setPackagesToScan("nl.ruudclaassen.movie_list.model");
		lcemfb.setPersistenceUnitName("PersistenceUnit");
		lcemfb.setJpaVendorAdapter(jpaVendorAdapter());
		lcemfb.setDataSource(dataSource);
		return lcemfb;
	}

	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hjva = new HibernateJpaVendorAdapter();
		hjva.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
		hjva.setGenerateDdl(true);
		hjva.setShowSql(true);

		return hjva;
	}

	@Bean
	@Lazy
	public FormattingConversionServiceFactoryBean formattingService() {
		FormattingConversionServiceFactoryBean bean = new FormattingConversionServiceFactoryBean();
		Set<Formatter> format = new HashSet<Formatter>();
		format.add(genreFormatter);
		bean.setFormatters(format);

		return bean;
	}

}
