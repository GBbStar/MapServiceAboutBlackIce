# 제18회 임베디드 SW 경진대회 [자유공모]

### 팀명 : 알맹이[전소라, 이준수, 신정희, 황규빈]
### 작품명 : 도로 위 블랙아이스 구간 파악 및 사고 예방
### 작품설명 : 블랙아이스가 발생한 구간을 파악하여 기준에 따라 속도 변경에 대한 경고 안내를 제공하거나 새로운 경로를 가도록 제안한다

* 주제 선정 이유   
-차가 주행하면 도로 표면에 따라 일정 수준의 진동이 발생하는데 블랙아이스가 발생하는 구간에서는 차량이 미끄러지거나 미끄러운 표면을 지나게 되면서 진동의 값이 줄어들 것으로 예상했다. 2. 차량이 블랙아이스 구간을 지나게 되면 일반 도로의 거칠기와 달리 미끄럽고 덜 거친 표면을 지난다는 것을 기반으로 간이 도로 상태를 제작하여 실험을 진행하였다.   
<br><br><br>
* 프로젝트 설명<br>
1. 실험 RC카들로 블랙아이스를 판단할 수 있는 기준값을 설정하였다.<br>
2. 주행을 하게 될 때 차량에서 얻어지는 실시간 데이터를 블루투스를 통해 제작한 앱으로 넘어가게 한다.<br>
3. 실험을 거쳐서 얻은 기준값을 토대로 실시간으로 들어오는 데이터를 비교하여 블랙아이스 여부를 판단한다. <br>
4. 여기서 블랙아이스라고 판단이 되면 경고 메시지를 제공하면서 운전자가 속도를 줄일 수 있게 한다. <br>
5. 또한 현재의 위도 경도를 데이터베이스에 넣어 다른 운전자들에게도 그 구역에 도달하면 경고 메시지를 제공할 수 있게 한다. <br>
6. 해당 블랙아이스 구간을 다시 지나는 차량이 얻은 데이터가 더 이상 블랙아이스가 아니라고 판단한다면 데이터베이스에서 해당 위치를 삭제하게 된다. <br>
7. 데이터 값을 받는 것과 동시에 차량이 위치를 바꿀 때 마다 적절한 범위를 설정해 주변에 블랙아이스의 위치를 지도에 표시한다. <br>
8. 해당 위도와 경도에 뒤따라오는 차량들이 구간을 지나게 될 때 자동으로 속도를 감속하게 할 수도 있을 것으로 예상된다. <br>
<br><br><br>
* 기능 목적 및 예상 기대<br>
경로를 이탈하지 않으면서 안전한 주행이 가능하도록 사고 예방을 하는 것이 목적이다. <br>
현재까지는 해당 차량 주변에 블랙아이스가 위치했을 경우에 경고 메시지를 제공하는 것이었다면, 근본적인 문제를 해결하기 위해 추가적인 방안을 마련하였다. <br>
그것은 해당 차량 주변의 국부적인 블랙아이스 위치만 볼 수 있는 것이 아니라, 지도상에서 전체적인 블랙아이스 위치를 확인할 수 있게 했다. <br>
이를 통해 담당 공무원은 해당 지역을 찾아 결빙을 해소할 수 있고, 사고의 원인을 줄이면서 운전자들의 보다 안전한 운행이 가능하게 할 수 있다. <br>
이 기술의 적용가능성은 신 차뿐만 아니라 기존의 차량들에도 센서만 부착하면 도입이 가능할 것으로 예상되고, 이는 블랙아이스를 해결하기 위해 도로에 열선을 설치하는 것보다 더욱 효과적이고, 자동차 업계에서 새로운 분야로의 발전을 엿볼 수 있을 것으로 예상된다. 
