
/*
Arduino-Interface-with-MySQL-for-storing-RFID-access-details (ARDUINO UNO,ETHERNET SHIELD, RFID MFRC522 & MYSQL)

     


  The circuit:
  * Typical pin layout used:(MFRC 522)
 * -----------------------------------------------------------------------------------------
 *             MFRC522      Arduino       Arduino   Arduino    Arduino          Arduino
 *             Reader/PCD   Uno/101       Mega      Nano v3    Leonardo/Micro   Pro Micro
 * Signal      Pin          Pin           Pin       Pin        Pin              Pin
 * -----------------------------------------------------------------------------------------
 * RST/Reset   RST          9             9         D9         RESET/ICSP-5     RST
 * SPI SS      SDA(SS)      4/10          4/53      D10        10               10
 * SPI MOSI    MOSI         11 / ICSP-4   51        D11        ICSP-4           16
 * SPI MISO    MISO         12 / ICSP-1   50        D12        ICSP-1           14
 * SPI SCK     SCK          13 / ICSP-3   52        D13        ICSP-3           15
 
 created 21 NOV 2016
 by Md. Fokhor Uddin Tareq

 For any help contact info@deligence.com

 */

#include <SPI.h>
#include <MFRC522.h>
//#include <SoftwareSerial.h>
#include <Ethernet.h>
#include <Wire.h> 
#include <LiquidCrystal_I2C.h>



#define LED_G 22 //define green LED pin
#define LED_R 23 //define red LED
#define BUZZER 24 //buzzer pin

const int trigPin = 30;
const int echoPin = 31;
long duration;
int distance;
int safetyDistance;

LiquidCrystal_I2C lcd(0x3F,20,4);  // set the LCD address to 0x27 for a 16 chars and 2 line display


//SoftwareSerial mySerial(8, 9);

// Arduino Mac Address
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

// Router DNS Server IP:
//IPAddress dnServer(192, 168, 5, 1);
// Router's Gateway Address:
IPAddress gateway(192, 168, 5, 1);
// Router Subnet MASK:
IPAddress subnet(255, 255, 255, 0);

//the IP address is dependent on your network
IPAddress ip(192, 168, 5, 111);

//Ethernet Client
EthernetClient client;
IPAddress server(192, 168, 5, 110);

// RFID and Card Reader
#define SS_PIN 53 //FOR RFID SS PIN BECASUSE WE ARE USING BOTH ETHERNET SHIELD AND RS-522
#define RST_PIN 44

MFRC522 rfid(SS_PIN, RST_PIN);
MFRC522::MIFARE_Key key;

byte id_temp[3][3];
byte i;
int j = 0;
int altasonic = 0;
void setup()
{
    pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
    pinMode(echoPin, INPUT); // Sets the echoPin as an Input
    
    Serial.begin(9600);
    lcd.init();                      // initialize the lcd 
    lcd.init();
//    mySerial.begin(9600);
    SPI.begin();
    rfid.PCD_Init();
    rfid.PCD_DumpVersionToSerial();
    for (byte i = 0; i < 6; i++) {
        key.keyByte[i] = 0xFF;
    }
    Ethernet.begin(mac, ip, gateway, subnet);
    delay(1000);
    Serial.println("connecting...");
    

    // Print a message to the LCD.
    lcd.backlight();
    lcd.setCursor(3,0);
    lcd.print("Waiting to");
    lcd.setCursor(2,1);
    lcd.print("Read The Card");

    pinMode(LED_G, OUTPUT);
  pinMode(LED_R, OUTPUT);
  pinMode(BUZZER, OUTPUT);
  noTone(BUZZER);
}
void loop()
{

    // Clears the trigPin
    digitalWrite(trigPin, LOW);
    delayMicroseconds(2);
    
    // Sets the trigPin on HIGH state for 10 micro seconds
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10);
    digitalWrite(trigPin, LOW);
    
    // Reads the echoPin, returns the sound wave travel time in microseconds
    duration = pulseIn(echoPin, HIGH);
    
    // Calculating the distance
    distance= duration*0.034/2;
    
    safetyDistance = distance;

      if (safetyDistance <= 10){
        
       altasonic =1;
      }

      

      
    int m = 0;
    int rfid_found1;
    int rfid_found2;
    String cardId = String("");
    String request2 = "GET /ethernetsend2.php";


    
    //look for new cards
    if (!rfid.PICC_IsNewCardPresent()){

      if ( altasonic == 1 ){
        
        if (client.connect(server, 80)) {
        client.println(request2);
        }
        lcd.clear();
    lcd.backlight();
    lcd.setCursor(3,0);
    lcd.print("No RFID");
    lcd.setCursor(2,1);
    lcd.print("Get A Card");

    digitalWrite(LED_R, HIGH);
    delay(1000);
    digitalWrite(LED_R, LOW);
    Serial.println("RFID card not found");
    delay(6000);
    lcd.clear();
    lcd.backlight();
    lcd.setCursor(3,0);
    lcd.print("Waiting to");
    lcd.setCursor(2,1);
    lcd.print("Read The Card");
    
    altasonic = 0; 
    client.stop();
    }
    return;

    }
    //select one of the cards
    if (!rfid.PICC_ReadCardSerial())
    return;

   


    

    for (i = 0; i < 4; i++) {
        cardId = cardId + rfid.uid.uidByte[i];
        
        //delay(10);
    }
//    for (int s = 0; s < 4; s++) {
//      Serial.print(rfid.uid.uidByte[s]);
//    }

    Serial.println(cardId);
    delay(50);
    Sending_To_DB(cardId);
    Serial.println("Data Written To DB");
    

    // Halt PICC
    rfid.PICC_HaltA();

    // Stop encryption on PCD
    rfid.PCD_StopCrypto1();
    //////////////////////

    // Print a message to the LCD.
    lcd.clear();
    lcd.backlight();
    lcd.setCursor(3,0);
    lcd.print("Reading End");
    lcd.setCursor(2,1);
    lcd.print("Free To Go");

    digitalWrite(LED_G, HIGH);
    tone(BUZZER, 500);
    delay(300);
    noTone(BUZZER);
    digitalWrite(LED_G, LOW);

    
    delay(2000);
    lcd.clear();
    lcd.backlight();
    lcd.setCursor(3,0);
    lcd.print("Waiting to");
    lcd.setCursor(2,1);
    lcd.print("Read The Card");

    

}


void Sending_To_DB(String data) //CONNECTING WITH MYSQL
{
  String request = "GET /ethernetsend.php?rfid_no="+ data +" HTTP/1.1"; 
  // if there's a successful connection:
  if (client.connect(server, 80)) {
    Serial.println("connected");
    // send the HTTP GET request:
    client.println(request);
    client.println("Host: 192.168.5.110");
    client.println("User-Agent: arduino-ethernet");
    client.println("Connection: close");
    client.println();

    // note the time that the connection was made:
   ///*** lastConnectionTime = millis();
  } else {
    // if you couldn't make a connection:
    Serial.println("connection failed");
    digitalWrite(LED_R, HIGH);
    tone(BUZZER, 300);
    delay(1000);
    digitalWrite(LED_R, LOW);
    noTone(BUZZER);
  }

  



  
   client.stop();
}
