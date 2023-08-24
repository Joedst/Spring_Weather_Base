package se.joedst.day1.services;

import se.joedst.day1.models.Forecast;

import java.util.ArrayList;
import java.util.List;

public class ForecastService {
    private static List<Forecast> forecasts = new ArrayList<Forecast>();

    public static List<Forecast> getForecasts() {
        return forecasts;
    }

    public void add(Forecast forecast) {
        { forecast.add(forecast); }
    }
}
//Bara här det behövs ändras för att byta från att spara i temporär list till databas.
