package se.joedst.day1;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.joedst.day1.models.Forecast;
import se.joedst.day1.services.ForecastService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class Day1Application implements CommandLineRunner {

	@Autowired
	private  ForecastService forecastService;

	public static void main(String[] args) {

		SpringApplication.run(Day1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var forecast = new Forecast();
		forecast.setId(UUID.randomUUID());
		forecast.setTemperature(12f);
		forecast.setDate(20230101);
		forecast.setHour(12);
		var objectMapper = new ObjectMapper(); //Kollar på properties o metadata och skapar JSON med dessa properties
		String json = objectMapper.writeValueAsString(forecast);
		System.out.println(json);

		var scan = new Scanner(System.in);

		Forecast forecast2 = objectMapper.readValue(json,Forecast.class); //Läser sedan JSONen o gör den till objekt.




		while(true){
			System.out.println("1. List all");
			System.out.println("2. Create");
			System.out.println("9. Exit");
			System.out.print("Action:");
			int sel = scan.nextInt();
			if(sel == 1){
				listPredictions();
			}
			else if(sel == 2){
				addPrediction(scan);
			}
			else if(sel == 9){
				break;
			}

		}
	}

	private void updatePrediction(Scanner scan){
		listPredictions();
		System.out.println("Ange vilken du vill uppdatera:");
		int num = scan.nextInt();
		var forecast = forecastService.getByIndex();
		System.out.printf("%d &d CURRENT: %f %n"),
		forecast.getDate(),
		forecast.getHour(),
		forecast.getTemperature();
		();

		System.out.println("Ange ny temp");
		float temp = scan.nextFloat();
		forecast.setTemperature




	}
	private void addPrediction(Scanner scan) {
		//Input på dag, hour, temp
		//Anropa servicen - Save
		System.out.println("*** CREATE PREDICTION ***");
		System.out.printf("Ange vilken dag:");
		int dag = scan.nextInt() ;
		System.out.print("Hour:");
		int hour =  scan.nextInt() ;
		System.out.print("Temperature:");
		float temp =  scan.nextFloat() ;

		var forecast = new Forecast();
		forecast.setId(UUID.randomUUID());
		forecast.setDate(dag);
		forecast.setHour(hour);
		forecast.setTemperature(temp);

		forecastService.add(forecast);
	}
	private void listPredictions() {
		for(var forecast : forecastService.getForecasts()){
			System.out.printf("%d %d %f %n",
					forecast.getDate(),
					forecast.getHour(),
					forecast.getTemperature()
			);
		}
	}
}