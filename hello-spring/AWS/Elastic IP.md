7.EIP
============
: EC2 와 NLB, Nat Gateway등에 고정 아이피를 부여

#### ENI(Elastic Network Interface)
:가상 RAM 카드
- MAC address
- 원본/대상 확인
- 한 개 이상의 보안그룹
- 한 개의 메인 프라이빗 IPv4
- 한 개 이상의 보조 프라이빗 IPv4
- 한 개 이상의 IPv6 주소
- 하나의 퍼블릭 IPv4 주소  
** 하나의 EC2 에 여러개의 ENI 가능  

** EC2 가 STOP 후 다시 실행 되면 다른 IP를 가지게 된다. 
   이때 통신이 어렵기 때문에 EIP 를 활용한다.
