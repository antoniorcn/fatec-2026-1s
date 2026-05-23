
#define LED_PIN_A 23
#define LED_PIN_B 25

// the setup function runs once when you press reset or power the board
void setup() {
  pinMode( LED_PIN_A, OUTPUT );
  pinMode( LED_PIN_B, OUTPUT );
}

// the loop function runs over and over again forever
void loop() {
  digitalWrite(LED_PIN_A, HIGH);
  digitalWrite(LED_PIN_B, LOW);
  delay(500);
  digitalWrite(LED_PIN_A, LOW);
  digitalWrite(LED_PIN_B, HIGH);
  delay(500);
}
