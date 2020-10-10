#include <stdio.h>
#include <LiquidCrystal_I2C.h>
#include <SoftwareSerial.h>
#include <SPI.h>
#include <SD.h>
LiquidCrystal_I2C lcd(0x27,16,2);
File myFile;
int piezoPin=A0; // float로 해보기
int Resistor=A1; 
int IR_R=A2;
int IR_L=A3;
int motorBR_Control=5;//PWM
int motorBR_a=9;
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
int blue_TX=1;
int blue_RX=0;
SoftwareSerial mySerial(blue_TX,blue_RX);
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
int speed_L=110; //이후, 함수로 값 만들어주기
int speed_M=120;
int speed_U=130;
int speed_TTF=220;

void setup() 
{
  mySerial.begin(9600);
  Serial.begin(9600);
  Serial.println("StartS"); //#1
  Serial.println("SD card initialization");//#2
  //if (!SD.begin(4))
  //{
  //  Serial.println("fail to initialization");
  //  while(1);
  //}
  Serial.println("Real Ready!");//#3
  //myFile=SD.open("test010.txt",FILE_WRITE);
  //myFile.close();
  pinMode(motorBR_a,OUTPUT);
  pinMode(motorBR_b,OUTPUT);
  pinMode(motorBL_a,OUTPUT);
  pinMode(motorBL_b,OUTPUT);
}

void loop() 
{
  for (in=1;in<101;in++)
  {
    hey=in;
    speed_K= hey/2+150;
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
    if (IRL_VALUE==111 && IRR_VALUE==111) // 직진
    {
      digitalWrite(motor_BR_a,LOW);
      digitalWrite(motor_BR_b,HIGH); //정방향
      analogWrite(motorBR_Control,speed_K);
      digitalWrite(motorBL_a,HIGH);//정방향
      digitalWrite(motorBL_b,LOW);
      analogWrite(motorBL_Control,speed_K);
      delay(10);
    }
    else if ((IRL_VALUE==111 && IRR_VALUE==999) // 약간 좌회전
    {
      digitalWrite(motor_BR_a,LOW);
      digitalWrite(motor_BR_b,HIGH); //정방향
      analogWrite(motorBR_Control,speed_K);
      digitalWrite(motorBL_a,LOW); //역방향
      digitalWrite(motorBL_b,HIGH);
      analogWrite(motorBL_Control,speed_K);
      delay(10);
    }
    else if ((IRL_VALUE==999 && IRR_VALUE==111) // 약간 우회전
    {
      digitalWrite(motor_BR_a,HIGH);
      digitalWrite(motor_BR_b,LOW); //역방향
      analogWrite(motorBR_Control,speed_K);
      digitalWrite(motorBL_a,HIGH); //정방향
      digitalWrite(motorBL_b,LOW);
      analogWrite(motorBL_Control,speed_K);
      delay(10);
    }
    else //OFF
    {
      digitalWrite(motor_BR_a,LOW);
      digitalWrite(motor_BR_b,LOW);
      analogWrite(motorBR_Control,speed_K);
      digitalWrite(motorBL_a,LOW);
      digitalWrite(motorBL_b,LOW);
      analogWrite(motorBL_Control,speed_K);
      exit(0);
    }
    R_V=analogRead(Resistor);
    //Serial.println("Resistor");//#4R1
    //Serial.println(R_V);//#5R2
    OSC=analogRead(piezoPin);
    //Serial.println("Oscillation");//#6R3
    //Serial.println(OSC);//#7R4
    //Serial.println("Resistor/division by IW");//#8R5
    Resistor_f=R_V/10; // 나누는 값은 테스트 후 조절해주기 // R_V는 기본적으로 10보다 클 때 이 줄부터 실행(새 스케치 오픈)
    //Srial.println(Resistor_f,6);//#9R6
    //Serial.println("Oscillation with R");//#10R7
    OSC_f=OSC*Resistor_f; // 값 증폭(Resistor_f>1)
    Serial.println(OSC_f,6);//#11R8
    mySerial.println(OSC_f);  //수정
    delay(10);//0.01초(8초에 2.5미터~1.125km/h)
    //myFile=SD.open("test010.txt",FILE_WRITE);
    //myFile.print("'");
    //myFile.print(trial);
    //myFile.println("'");
    //myFile.println(OSC_f);
    //myFile.close();
    trial=trial+1;
    Trial=1/0.01; //800 //distance=250cm가 8초니까/delay 0.05초
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
//if(IRL_VALUE=111 && IRL_VALUE=111)~ control 코드 작성하기 
