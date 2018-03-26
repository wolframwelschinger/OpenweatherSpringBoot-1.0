# Killt den Prozess
echo "Kille den Prozess..." 
pkill -f "java -jar target/OpenWeather"

# 2 Sekunden pausieren
sleep 30

# Prozess neu starten
echo "Prozess neu starten"
/usr/bin/java -jar target/OpenWeather-0.0.1-SNAPSHOT.jar 
 

