#!/bin/bash

curl -i -X GET -H "Content-Type:application/json" http://api.openweathermap.org/data/2.5/find?q=Barcelona&units=metric&type=accurate&mode=json&APPID=a69fc6770115d5a82cd0d37e359ad4bf&lang=de
