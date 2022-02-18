package com.example.balancechecker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.util.Objects;

public class WeatherController {

    @FXML
    private ImageView imgBackground;

    @FXML
    private Text txtWeatherCelcius;

    @FXML
    private Label lblWeatherInfo;

    @FXML
    private Label lblTime;

    @FXML
    private TextField txtFldSearchBar;

    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";

    @FXML
    void getWeatherData(ActionEvent event) throws MalformedURLException {
        JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());

        System.out.println(todaysWeather);
        txtWeatherCelcius.setText("" + String.format("%.0f", todaysWeather.get("the_temp")) + " C°");
        lblWeatherInfo.setText((String) todaysWeather.get("weather_state_name"));
        //Todo: Get Local City Time and display it correctly!

        //For Debug
        String Celcius;
        Celcius = String.valueOf(todaysWeather.get("the_temp"));
        System.out.println(Celcius);
    }

    public String getWoeid() throws MalformedURLException {

        APIConnector apiConnectorCity = new APIConnector(cityAPI);

        JSONObject jsonData = (JSONObject) (apiConnectorCity.getJSONArray(txtFldSearchBar.getText())).get(0);

        System.out.println(jsonData);
        return jsonData.get("woeid").toString();
    }

    public JSONObject GetTodaysWeatherInformation(String woeid) throws MalformedURLException {

        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");

        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");

        return (JSONObject) weatherArray.get(0);
    }
}
