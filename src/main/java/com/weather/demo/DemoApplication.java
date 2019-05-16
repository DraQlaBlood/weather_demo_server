package com.weather.demo;

import com.weather.demo.controller.WeatherController;
import com.weather.demo.dao.AgencyDAO;
import com.weather.demo.dao.MetricDAO;
import com.weather.demo.dao.RegionDAO;
import com.weather.demo.dao.StationDAO;
import com.weather.demo.model.Agency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.UUID;
import java.util.stream.Stream;

import static java.util.UUID.*;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	/*@Component
	@Order(1)
	class  AgencyCommandLineRunner implements CommandLineRunner{

		private final Logger logger = LoggerFactory.getLogger(AgencyCommandLineRunner.class);

		@Autowired
		AgencyDAO agencyDAO;

		public AgencyCommandLineRunner(AgencyDAO agencyDAO) {
			this.agencyDAO = agencyDAO;
		}

		@Override
		public void run(String... args) throws Exception {

			logger.info("Loading agency data....");

			new WeatherController().saveAgency(new String ["",("Environment Canada"), "gov", "ca","https" +
					"://weather.gc" +
				".ca")]);
			new WeatherController().saveAgency(new Agency(randomUUID(),"National Oceanic And Athmospheric Administration","gov","us","https://www.weather.gov/"));
			new WeatherController().saveAgency(new Agency(randomUUID(),"Meteo Group","com","global","https://www.meteogroup.com"));
			new WeatherController().saveAgency(new Agency(randomUUID(),"World Meteoroligcal Organization","int","global",	"https://public.wmo.int/en"));
		}
	}*/

}
