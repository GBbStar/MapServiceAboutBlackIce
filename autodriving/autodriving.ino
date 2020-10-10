#include <stdio.h>
#include <LiquidCrystal_I2C.h>
#include <SoftwareSerial.h>
#include <SPI.h>
#include <SD.h>
LiquidCrystal_I2C lcd(0x27,16,2);
#include <SoftwareSerial.h>

int piezoPin=A0; // float로 해보기
int Resistor=A1; 
int IR_R=A2;
int IR_L=A3;
int D_SDA=A4;
int D_SCL=A5;
int motorBR_Control=5;//PWM
int motorBR_a=4;
int motorBR_b=10;
int motorBL_Control=6;//PWM
int motorBL_a=7;
int motorBL_b=8;
int IRL_value=0;
int IRR_value=0;
int IRR_VALUE=0;
int IRL_VALUE=0; //range:0~1023
int in=1;
int speed_K=0;
int hey=0;
//Bluetooth
SoftwareSerial bluetooth(3,2);
//Variable
int IR_RV=0;
int IR_LV=0;
int R_V=0;
int trial=0;
int Trial=0;
float Resistor_f =0; // 0.xxxxxx까지만
int OSC=0;
float OSC_f=0; // 0.xxxxxx까지만
//Motor control
int speed_Z=0;
int speed_L=60; //이후, 함수로 값 만들어주기
int speed_M=100;
int speed_U=180;
int speed_TTF=225;

void setup() 
{
  lcd.init();
  lcd.backlight();
  lcd.setCursor(0,0);
  lcd.print("StartD");
  Serial.begin(9600);
  bluetooth.begin(9600); //%%%
  Serial.println("AT command"); //%%%
  pinMode(motorBR_a,OUTPUT);
  pinMode(motorBR_b,OUTPUT);
  pinMode(motorBL_a,OUTPUT);
  pinMode(motorBL_b,OUTPUT);
}

void loop() 
{
  for (in=1;in<601;in++)
  {
    hey=in;
    speed_K= 120+in/6; //25~225
    digitalWrite(motorBR_a,LOW);//alow_bhigh=straight
    digitalWrite(motorBR_b,HIGH);
    analogWrite(motorBR_Control,speed_K);//속도 고정(이후에는 새로 변수 설정해서 함수 도입, 새 스케치 오픈)
    digitalWrite(motorBL_a,HIGH);//ahigh_blow=straight
    digitalWrite(motorBL_b,LOW);
    analogWrite(motorBL_Control,speed_K);
    IRR_value=analogRead(IR_R); 
    IRL_value=analogRead(IR_L);
    //Serial.println(IRR_value);
    //Serial.println(IRL_value);
    if (IRL_value<=200 && IRL_value>0)
    {
      IRL_VALUE=111; //L_white
    }
    else
    {
      IRL_VALUE=999; //L_black
    }
    if (IRR_value<=200 && IRR_value>0)
    {
      IRR_VALUE=111; //L_white
    }
    else
    {
      IRR_VALUE=999; //L_black
    } 
    R_V=analogRead(Resistor);
    Serial.print("test:");/////////////////////////
    Serial.println(R_V);/////////////////////////
    OSC=analogRead(piezoPin);
    Resistor_f=R_V/3;
    OSC_f=OSC*Resistor_f; // 값 증폭(Resistor_f>1)
    Serial.println(OSC_f,6);//#11R8
    bluetooth.println(OSC_f); 
    delay(10);//0.01초(8초에 2.5미터~1.125km/h)
    trial=trial+1;
    Trial=6*100; //200 //distance=250cm가 8초니까/delay 0.05초
    if(trial==Trial)
    {
      digitalWrite(motorBR_a,LOW);
      digitalWrite(motorBR_b,LOW);
      digitalWrite(motorBL_a,LOW);
      digitalWrite(motorBL_b,LOW);
      exit(0);
    }
  }
}
