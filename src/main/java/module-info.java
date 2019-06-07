module WeatherApp
{
    requires javafx.fxml;
    requires javafx.controls;
    requires api.core;
    requires api.common;
    requires api.model;
    requires api.query;
    requires java.sql;

    opens app;
    opens app.controllers;
    opens app.database;
    opens app.weather.info;
}