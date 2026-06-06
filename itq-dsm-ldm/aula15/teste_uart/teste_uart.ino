#include <DHT.h>

#include <WiFi.h>
#include <PubSubClient.h>
#include "DHT.h"
#define LED 2
#define DHTPIN 23

int i = 0;
const char* ssid = "TOM-TESTE";
const char* password =  "01234567";
const char* mqtt_server = "10.196.121.237";
const int mqtt_port = 1883;

WiFiClient espClient;
PubSubClient mqttClient(espClient);

DHT dht(DHTPIN, DHT11);

void reconnect() {
  while (!mqttClient.connected()) {
    // Attempt to connect with a unique Client ID
    Serial.println("Conectando no MQTT Server");
    if (mqttClient.connect("ESP32Client")) {
      Serial.println("Connected to MQTT Broker");
    } else {
      delay(5000);
    }
  }
}

void setup() {
  Serial.begin(9600);
  pinMode(LED, OUTPUT);
  WiFi.begin(ssid, password);
  dht.begin();

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");
  Serial.println("Endereço de IP: ");
  Serial.println(WiFi.localIP());
  Serial.println("Conectando no MQTT Server");
  mqttClient.setServer( mqtt_server, mqtt_port );
  reconnect();
}



void loop() {
  digitalWrite(LED, HIGH);
  delay(1000);
  Serial.printf("Executado %d\n", i);
  i++;
  digitalWrite(LED, LOW);
  delay(1000);
  float umidade = dht.readHumidity();
  float temperatura = dht.readTemperature(); // Leitura em graus Celsius

  // Verifica se alguma leitura falhou para evitar dados incorretos
  if (isnan(umidade) || isnan(temperatura)) {
    Serial.println("Falha ao ler o sensor DHT11!");
    return;
  }

  char buffer[100]; // Buffer to store the result

  // Create the formatted string
  // 10.0;50.0
  // {"temp": 10.0, "umid": 50.0}
  sprintf(buffer, "{\"temp\": %5.1f, \"umid\": %5.1f}", temperatura, umidade);

  reconnect();
  mqttClient.publish("sensor/dht11", buffer);
}
