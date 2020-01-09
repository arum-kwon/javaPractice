package game.onecard;
//if, for, println, 기본적은 비교 조건 반복 연산 사용
//결과 : 두 컴퓨터가 원카드 하는 것

/* 카드 종류 : 49(1) 50(2) 51(3) 52(4) 53(5) 54(6) 55(7) 56(8) 57(9) 75(K) 74(J) 81(Q) X 35(#) 64(@)
 * 룰
 *  (1) 숫자와 패턴을 각각 char로 지정한다 총 카드는 12*2 = 24개, 각각 6개씩 들고 시작, 중앙패 12개
 *  (2) 숫자와 패턴은 둘이 한 세트가 되어야함.
 *  (3) 각각 p2/p2의 카드 1의 n/p ... 카드6의 n/p 구분
 *  (4) p1, p2, 중앙 각각 소주할 수 있는 카드 목록 max 24개 
 *  0. 시작할 때 각각 플레이어의 카드1에서 6까지 숫자와 패턴을 넣어준다.
 *  1. p1,p2,중앙의 카드 배정은 수동으로 // char p1_c1_p = 35/char p1_c1_n = 50 
 *  	(1) p1c1p = 35,	p1c1n = 50
 *  		p1c1p = 35,	p1c1p = 53
 *  				...
 *  
 *  2. 중복 패가 없는지 확인한다.
 *  	(1) 모든 n의 합 707 (1~9:477, KJQ) * 2세트 = 1414 / 모든 p의 합 99 * 12세트 = 1188 p1와 p2, ce의 모든 np의 합을 비교
 *  		(1-1) 위의 합이 다르면, 종료
 *  		(1-2) 위의 합이 같으면, 다음 단계로 	
 *  3. 화면 꾸리기
 *      -----------------------------
 *      p1: #6 @4 #1 @8 #7
 *           #5             n턴
 *      p2: @2 @7 @8 #4 @K   
 *      -----------------------------
 *      (1)" -----------------------------"
 *      (2)"p1: "+ (char)p1c1p + (char)p1c1n + " " + (char)p1c1p + ... + p1c24n
 *      (3)"   " + cec1p + cec1n + "        " + numTurn + "턴"
 *      (4)"p1: "+ (char)p1c1p + (char)p1c1n + " " + (char)p1c1p + ... + p2c24n
 *      (5)" -----------------------------"
 * 
 *  4. 턴 시작 (순서 p1 -> p2)
 *  	//p1
 * 		(1) if(p1c1p == cec1p || p1c1n == cec1n) //패턴이 존재하면, p1c->(cec1->cec)
 * 			(1-1) if(cec2p == 0 && cec2n == 0) // 비어있는 c넘버 탐색
 * 				  else if(cec3p == 0 && cec3n == 0)
 * 							  ....
 * 				  else if(cec24p == 0 && cec24n == 0)
 * 				(1-1-1) (해당 조건문 안에)  //cec1을 비어있는 c넘버에 저장
 * 						cec24p = cec1p;
 * 						cec24n = cec1n;
 * 			(1-2) cec1p = p1c1p; cec1n = p1c1n; //p1c를 cec1에 저장
 * 			(1-3) p1c1p = 0; p1c1n =0; //p1c를 0으로 비워주기
 * 		(2) else //만약 p1의 모든 패중에 일치하는 p or n 이 없으면 cec2->p1c
 * 			(2-1) if(p1c1p == 0 && p1c1n == 0) //비어있는 p1c탐색
 * 				  else if(p1c2p == 0 && p1c2n == 0)
 * 				    			....
 * 				  else if(p1c24p == 0 && p1c24n == 0)
 * 				(1-1-1) (해당 조건문 안에) //cec2를 비어있는 p1c에 저장 
 * 						p1c24p = cec2p;
 * 						p1c24n = cec2n;
 * 			(2-2) cec2p=cec3p; cec2n=cec3n; .. cec23p=cec24p; cec23n=cec24n; //cec(n-1) = cec(n) 다 대입  
 * 		//p2 위 복붙
 * 
 * 구체적인 룰 추가
 *	5. 가진 패가 없으면 승리 (191223)
 *  
 *
 *
 *
 *
 * */

public class OneCard {
	public static void main(String[] args) {
		//int trun = 1; //턴
		
		//p1 초기화
		char p1c1n=0, p1c2n=0, p1c3n=0, p1c4n=0, p1c5n=0, p1c6n=0,
			 p1c7n=0, p1c8n=0, p1c9n=0, p1c10n=0, p1c11n=0, p1c12n=0,
			 p1c13n=0, p1c14n=0, p1c15n=0, p1c16n=0, p1c17n=0, p1c18n=0, 
			 p1c19n=0, p1c20n=0, p1c21n=0, p1c22n=0, p1c23n=0, p1c24n=0,
		     p1c1p=0, p1c2p=0, p1c3p=0, p1c4p=0, p1c5p=0, p1c6p=0, 
		     p1c7p=0, p1c8p=0, p1c9p=0, p1c10p=0, p1c11p=0, p1c12p=0,
			 p1c13p=0, p1c14p=0, p1c15p=0, p1c16p=0, p1c17p=0, p1c18p=0, 
			 p1c19p=0, p1c20p=0, p1c21p=0, p1c22p=0, p1c23p=0, p1c24p=0,
		 //p2
			 p2c1n=0, p2c2n=0, p2c3n=0, p2c4n=0, p2c5n=0, p2c6n=0,
			 p2c7n=0, p2c8n=0, p2c9n=0, p2c10n=0, p2c11n=0, p2c12n=0,
			 p2c13n=0, p2c14n=0, p2c15n=0, p2c16n=0, p2c17n=0, p2c18n=0, 
			 p2c19n=0, p2c20n=0, p2c21n=0, p2c22n=0, p2c23n=0, p2c24n=0,
		     p2c1p=0, p2c2p=0, p2c3p=0, p2c4p=0, p2c5p=0, p2c6p=0,
		     p2c7p=0, p2c8p=0, p2c9p=0, p2c10p=0, p2c11p=0, p2c12p=0,
			 p2c13p=0, p2c14p=0, p2c15p=0, p2c16p=0, p2c17p=0, p2c18p=0,
			 p2c19p=0, p2c20p=0, p2c21p=0, p2c22p=0, p2c23p=0, p2c24p=0,
		 //c
			 cec1n=0, cec2n=0, cec3n=0, cec4n=0, cec5n=0, cec6n=0,
			 cec7n=0, cec8n=0, cec9n=0, cec10n=0, cec11n=0, cec12n=0,
			 cec13n=0, cec14n=0, cec15n=0, cec16n=0, cec17n=0, cec18n=0, 
			 cec19n=0, cec20n=0, cec21n=0, cec22n=0, cec23n=0, cec24n=0,
		     cec1p=0, cec2p=0, cec3p=0, cec4p=0, cec5p=0, cec6p=0, 
		     cec7p=0, cec8p=0, cec9p=0, cec10p=0, cec11p=0, cec12p=0,
			 cec13p=0, cec14p=0, cec15p=0, cec16p=0, cec17p=0, cec18p=0,
			 cec19p=0, cec20p=0, cec21p=0, cec22p=0, cec23p=0, cec24p=0;
		 //0.1. p1 배분
		 p1c1p=35;		p1c1n=49;
		 p1c2p=64;		p1c2n=74;
		 p1c3p=35;		p1c3n=50;
		 p1c4p=64;		p1c4n=75;
		 p1c5p=35;		p1c5n=51;
		 p1c6p=64; 		p1c6n=81;
		 // p2
		 p2c1p=64;		p2c1n=49;
		 p2c2p=35;		p2c2n=52;
		 p2c3p=64;		p2c3n=50;
		 p2c4p=35;		p2c4n=53;
		 p2c5p=64;		p2c5n=51;
		 p2c6p=35; 		p2c6n=54;
		 // ce
		 cec1p=35;		cec1n=55;
		 cec2p=64;		cec2n=52;
		 cec3p=35;		cec3n=56;
		 cec4p=64;		cec4n=53;
		 cec5p=35;		cec5n=57;
		 cec6p=64; 		cec6n=54;
		 cec7p=35;		cec7n=74;
		 cec8p=64;		cec8n=55;
		 cec9p=35;		cec9n=75;
		 cec10p=64;		cec10n=56;
		 cec11p=35;		cec11n=81;
		 cec12p=64; 	cec12n=57;
		 //2. 중복 패가 없는지 확인한다. 모든 n의 합  = 1414 / 모든 p의 합 = 1188 
		 int sumNUM = p1c1n + p1c2n + p1c3n + p1c4n + p1c5n + p1c6n
				      + p1c7n + p1c8n + p1c9n + p1c10n + p1c11n + p1c12n
				      + p1c13n + p1c14n + p1c15n + p1c16n + p1c17n + p1c18n
				      + p1c19n + p1c20n + p1c21n + p1c22n + p1c23n + p1c24n
				      + p2c1n + p2c2n + p2c3n + p2c4n + p2c5n + p2c6n
				      + p2c7n + p2c8n + p2c9n + p2c10n + p2c11n + p2c12n
				      + p2c13n + p2c14n + p2c15n + p2c16n + p2c17n + p2c18n
				      + p2c19n + p2c20n + p2c21n + p2c22n + p2c23n + p2c24n
				      + cec1n + cec2n + cec3n + cec4n + cec5n + cec6n
				      + cec7n + cec8n + cec9n + cec10n + cec11n + cec12n
				      + cec13n + cec14n + cec15n + cec16n + cec17n + cec18n
				      + cec19n + cec20n + cec21n + cec22n + cec23n + cec24n;
		 int sumPat = p1c1p + p1c2p + p1c3p + p1c4p + p1c5p + p1c6p
				      + p1c7p + p1c8p + p1c9p + p1c10p + p1c11p + p1c12p
				      + p1c13p + p1c14p + p1c15p + p1c16p + p1c17p + p1c18p
				      + p1c19p + p1c20p + p1c21p + p1c22p + p1c23p + p1c24p
				      + p2c1p + p2c2p + p2c3p + p2c4p + p2c5p + p2c6p
				      + p2c7p + p2c8p + p2c9p + p2c10p + p2c11p + p2c12p
				      + p2c13p + p2c14p + p2c15p + p2c16p + p2c17p + p2c18p
				      + p2c19p + p2c20p + p2c21p + p2c22p + p2c23p + p2c24p
				      + cec1p + cec2p + cec3p + cec4p + cec5p + cec6p
				      + cec7p + cec8p + cec9p + cec10p + cec11p + cec12p
				      + cec13p + cec14p + cec15p + cec16p + cec17p + cec18p
				      + cec19p + cec20p + cec21p + cec22p + cec23p + cec24p;
		 if(sumNUM != 1414 || sumPat != 1188) {
			 System.out.println(" error 2 : check card");
			 
		 }else {	//3.화면꾸리기
			 int winner =0;
			 for(int trun=1 ; trun<20 ; trun++) {

				 System.out.println("-------------------------------------");
				 System.out.println("p1 : " + p1c1p+p1c1n + " " + p1c2p+p1c2n + " " + p1c3p+p1c3n + " " + 
						 			p1c4p+p1c4n + " " + p1c5p+p1c5n + " " + p1c6p+p1c6n + " " + p1c7p+p1c7n + " " + 
						 			p1c8p+p1c8n + " " + p1c9p+p1c9n + " " + p1c10p+p1c10n + " " + p1c11p+p1c11n + " " + 
						 			p1c12p+p1c12n + " " + p1c13p+p1c13n + " " + p1c14p+p1c14n + " " + p1c15p+p1c15n + " " + 
						 			p1c16p+p1c16n + " " + p1c17p+p1c17n + " " + p1c18p+p1c18n + " " + p1c19p+p1c19n + " " + 
						 			p1c20p+p1c20n + " " + p1c21p+p1c21n + " " + p1c22p+p1c22n + " " + p1c23p+p1c23n + " " +
						 			p1c24p+p1c24n);
				 System.out.println("("+trun+"턴 P1)    " +cec1p+cec1n);
				 System.out.println("p1 : " + p2c1p+p2c1n + " " + p2c2p+p2c2n + " " + p2c3p+p2c3n + " " + 
				 			p2c4p+p2c4n + " " + p2c5p+p2c5n + " " + p2c6p+p2c6n + " " + p2c7p+p2c7n + " " + 
				 			p2c8p+p2c8n + " " + p2c9p+p2c9n + " " + p2c10p+p2c10n + " " + p2c11p+p2c11n + " " + 
				 			p2c12p+p2c12n + " " + p2c13p+p2c13n + " " + p2c14p+p2c14n + " " + p2c15p+p2c15n + " " + 
				 			p2c16p+p2c16n + " " + p2c17p+p2c17n + " " + p2c18p+p2c18n + " " + p2c19p+p2c19n + " " + 
				 			p2c20p+p2c20n + " " + p2c21p+p2c21n + " " + p2c22p+p2c22n + " " + p2c23p+p2c23n + " " +
				 			p2c24p+p2c24n);
				 System.out.println("-------------------------------------");
				 
				 if(winner != 0) {
					 System.out.println("p"+winner+"의 패가 없습니다. 승리");
					 break;
				 }
				 
				 //4. 턴 시작 (순서 p1 -> p2)
				 //p1 
				 boolean p1IsSame = false; //패턴이 존재하면 true, p1NumSame에 넘버 저장 
				 int p1NumSame = 0;
				 if(p1c1p == cec1p || p1c1n == cec1n) { p1IsSame=true; p1NumSame=1;}
				 else if (p1c2p == cec1p || p1c2n == cec1n) { p1IsSame=true; p1NumSame=2;}
				 else if (p1c3p == cec1p || p1c3n == cec1n) { p1IsSame=true; p1NumSame=3;}
				 else if (p1c4p == cec1p || p1c4n == cec1n) { p1IsSame=true; p1NumSame=4;}
				 else if (p1c5p == cec1p || p1c5n == cec1n) { p1IsSame=true; p1NumSame=5;}
				 else if (p1c6p == cec1p || p1c6n == cec1n) { p1IsSame=true; p1NumSame=6;}
				 else if (p1c7p == cec1p || p1c7n == cec1n) { p1IsSame=true; p1NumSame=7;}
				 else if (p1c8p == cec1p || p1c8n == cec1n) { p1IsSame=true; p1NumSame=8;}
				 else if (p1c9p == cec1p || p1c9n == cec1n) { p1IsSame=true; p1NumSame=9;}
				 else if (p1c10p == cec1p || p1c10n == cec1n) { p1IsSame=true; p1NumSame=10;}
				 else if (p1c11p == cec1p || p1c11n == cec1n) { p1IsSame=true; p1NumSame=11;}
				 else if (p1c12p == cec1p || p1c12n == cec1n) { p1IsSame=true; p1NumSame=12;}
				 else if (p1c13p == cec1p || p1c13n == cec1n) { p1IsSame=true; p1NumSame=13;}
				 else if (p1c14p == cec1p || p1c14n == cec1n) { p1IsSame=true; p1NumSame=14;}
				 else if (p1c15p == cec1p || p1c15n == cec1n) { p1IsSame=true; p1NumSame=15;}
				 else if (p1c16p == cec1p || p1c16n == cec1n) { p1IsSame=true; p1NumSame=16;}
				 else if (p1c17p == cec1p || p1c17n == cec1n) { p1IsSame=true; p1NumSame=17;}
				 else if (p1c18p == cec1p || p1c18n == cec1n) { p1IsSame=true; p1NumSame=18;}
				 else if (p1c19p == cec1p || p1c19n == cec1n) { p1IsSame=true; p1NumSame=19;}
				 else if (p1c20p == cec1p || p1c20n == cec1n) { p1IsSame=true; p1NumSame=20;}
				 else if (p1c21p == cec1p || p1c21n == cec1n) { p1IsSame=true; p1NumSame=21;}
				 else if (p1c22p == cec1p || p1c22n == cec1n) { p1IsSame=true; p1NumSame=22;}
				 else if (p1c23p == cec1p || p1c23n == cec1n) { p1IsSame=true; p1NumSame=23;}
				 else if (p1c24p == cec1p || p1c24n == cec1n) { p1IsSame=true; p1NumSame=24;}
				
				 if(p1IsSame) //패턴이 존재하면 비어있는 cec넘버 탐색해서 cec1을 저장하고, p1NumSame와 같은 넘버의 p1c를  cec1저장
				 {	
					 if(cec2p == 0 && cec2n == 0) { cec2p = cec1p; cec2n = cec1n;
					 }else if(cec3p == 0 && cec3n == 0) { cec3p = cec1p; cec3n = cec1n;
					 }else if(cec4p == 0 && cec4n == 0) { cec4p = cec1p; cec4n = cec1n;
					 }else if(cec5p == 0 && cec5n == 0) { cec5p = cec1p; cec5n = cec1n;
					 }else if(cec6p == 0 && cec7n == 0) { cec6p = cec1p; cec6n = cec1n;
					 }else if(cec7p == 0 && cec7n == 0) { cec7p = cec1p; cec7n = cec1n;
					 }else if(cec8p == 0 && cec8n == 0) { cec8p = cec1p; cec8n = cec1n;
					 }else if(cec9p == 0 && cec9n == 0) { cec9p = cec1p; cec9n = cec1n;
					 }else if(cec10p == 0 && cec10n == 0) { cec10p = cec1p; cec10n = cec1n;
					 }else if(cec11p == 0 && cec11n == 0) { cec11p = cec1p; cec11n = cec1n;
					 }else if(cec12p == 0 && cec12n == 0) { cec12p = cec1p; cec12n = cec1n;
					 }else if(cec13p == 0 && cec13n == 0) { cec13p = cec1p; cec13n = cec1n;
					 }else if(cec14p == 0 && cec14n == 0) { cec14p = cec1p; cec14n = cec1n;
					 }else if(cec15p == 0 && cec15n == 0) { cec15p = cec1p; cec15n = cec1n;
					 }else if(cec16p == 0 && cec16n == 0) { cec16p = cec1p; cec16n = cec1n;
					 }else if(cec17p == 0 && cec17n == 0) { cec17p = cec1p; cec17n = cec1n;
					 }else if(cec18p == 0 && cec18n == 0) { cec18p = cec1p; cec18n = cec1n;
					 }else if(cec19p == 0 && cec19n == 0) { cec19p = cec1p; cec19n = cec1n;
					 }else if(cec20p == 0 && cec20n == 0) { cec20p = cec1p; cec20n = cec1n;
					 }else if(cec21p == 0 && cec21n == 0) { cec21p = cec1p; cec21n = cec1n;
					 }else if(cec22p == 0 && cec22n == 0) {	cec22p = cec1p; cec22n = cec1n;
					 }else if(cec23p == 0 && cec23n == 0) { cec23p = cec1p; cec23n = cec1n;
					 }else if(cec24p == 0 && cec24n == 0) { cec24p = cec1p; cec24n = cec1n;
					 }
					 
					 if(p1NumSame == 1) { cec1p = p1c1p; cec1n = p1c1n; p1c1p = 0; p1c1n =0; //p1c를 cec1에 저장 및 비우기
					 }else if (p1NumSame == 2) { cec1p = p1c2p; cec1n = p1c2n; p1c2p = 0; p1c2n =0; 
					 }else if (p1NumSame == 3) { cec1p = p1c3p; cec1n = p1c3n; p1c3p = 0; p1c3n =0; 
					 }else if (p1NumSame == 4) { cec1p = p1c4p; cec1n = p1c4n; p1c4p = 0; p1c4n =0; 
					 }else if (p1NumSame == 5) { cec1p = p1c5p; cec1n = p1c5n; p1c5p = 0; p1c5n =0; 
					 }else if (p1NumSame == 6) { cec1p = p1c6p; cec1n = p1c6n; p1c6p = 0; p1c6n =0; 
					 }else if (p1NumSame == 7) { cec1p = p1c7p; cec1n = p1c7n; p1c7p = 0; p1c7n =0; 
					 }else if (p1NumSame == 8) { cec1p = p1c8p; cec1n = p1c8n; p1c8p = 0; p1c8n =0; 
					 }else if (p1NumSame == 9) { cec1p = p1c9p; cec1n = p1c9n; p1c9p = 0; p1c9n =0; 
					 }else if (p1NumSame == 10) { cec1p = p1c10p; cec1n = p1c10n; p1c10p = 0;p1c10n =0; 
					 }else if (p1NumSame == 11) { cec1p = p1c11p; cec1n = p1c11n; p1c11p = 0; p1c11n =0; 
					 }else if (p1NumSame == 12) { cec1p = p1c12p; cec1n = p1c12n; p1c12p = 0; p1c12n =0; 
					 }else if (p1NumSame == 13) { cec1p = p1c13p; cec1n = p1c13n; p1c13p = 0; p1c13n =0; 
					 }else if (p1NumSame == 14) { cec1p = p1c14p; cec1n = p1c14n; p1c14p = 0; p1c14n =0; 
					 }else if (p1NumSame == 15) { cec1p = p1c15p; cec1n = p1c15n; p1c15p = 0; p1c15n =0; 
					 }else if (p1NumSame == 16) { cec1p = p1c16p; cec1n = p1c16n; p1c16p = 0; p1c16n =0; 
					 }else if (p1NumSame == 17) { cec1p = p1c17p; cec1n = p1c17n; p1c17p = 0; p1c17n =0; 
					 }else if (p1NumSame == 18) { cec1p = p1c18p; cec1n = p1c18n; p1c18p = 0; p1c18n =0; 
					 }else if (p1NumSame == 19) { cec1p = p1c19p; cec1n = p1c19n; p1c19p = 0; p1c19n =0; 
					 }else if (p1NumSame == 20) { cec1p = p1c20p; cec1n = p1c20n; p1c20p = 0; p1c20n =0; 
					 }else if (p1NumSame == 21) { cec1p = p1c21p; cec1n = p1c21n; p1c21p = 0; p1c21n =0; 
					 }else if (p1NumSame == 22) { cec1p = p1c22p; cec1n = p1c22n; p1c22p = 0; p1c22n =0; 
					 }else if (p1NumSame == 23) { cec1p = p1c23p; cec1n = p1c23n; p1c23p = 0; p1c23n =0; 
					 }else if (p1NumSame == 24) { cec1p = p1c24p; cec1n = p1c24n; p1c24p = 0; p1c24n =0; 
					 }
					 
					 //카드를 제출하고, 카드가 없으면 종료함(1223)
					 if(p1c1p==0 && p1c1n==0 && p1c2p==0 && p1c2n==0 && p1c3p==0 && p1c3n==0 &&
						p1c4p==0 && p1c4n==0 && p1c5p==0 && p1c5n==0 && p1c6p==0 && p1c6n==0 && 
						p1c7p==0 && p1c7n==0 && p1c8p==0 && p1c8n==0 && p1c9p==0 && p1c9n==0 && 
						p1c10p==0 && p1c10n==0 && p1c11p==0 && p1c11n==0 && p1c12p==0 && p1c12n==0 && 
						p1c13p==0 && p1c13n==0 && p1c14p==0 && p1c14p==0 && p1c15p==0 && p1c15n==0 && 
						p1c16p==0 && p1c16n==0 && p1c17p==0 && p1c17p==0 && p1c18p==0 && p1c18n==0 && 
						p1c19p==0 && p1c19n==0 && p1c20p==0 && p1c20p==0 && p1c21p==0 && p1c21n==0 && 
						p1c22p==0 && p1c22n==0 && p1c23p==0 && p1c23n==0 && p1c24p==0 && p1c24n==0) {
						 winner=1;
						 continue;
					 }
					 
				 }else { //(2)만약 p1의 모든 패중에 일치하는 p or n 이 없으면, 비어있는 p1c에 cec2삽입 , (n:2>23)cec(n) = cec(n+1) 다 대입
					 if(p1c1p == 0 && p1c1n == 0) { p1c1p = cec2p; p1c1n = cec2n;
					 } else if(p1c2p == 0 && p1c2n == 0) { p1c2p = cec2p; p1c2n = cec2n;
					 } else if(p1c3p == 0 && p1c3n == 0) { p1c3p = cec2p; p1c3n = cec2n;
					 } else if(p1c4p == 0 && p1c4n == 0) { p1c4p = cec2p; p1c4n = cec2n;
					 } else if(p1c5p == 0 && p1c5n == 0) { p1c5p = cec2p; p1c5n = cec2n;
					 } else if(p1c6p == 0 && p1c6n == 0) { p1c6p = cec2p; p1c6n = cec2n;
					 } else if(p1c7p == 0 && p1c7n == 0) { p1c7p = cec2p; p1c7n = cec2n;
					 } else if(p1c8p == 0 && p1c8n == 0) { p1c8p = cec2p; p1c8n = cec2n;
					 } else if(p1c9p == 0 && p1c9n == 0) { p1c9p = cec2p; p1c9n = cec2n;
					 } else if(p1c10p == 0 && p1c10n == 0) { p1c10p = cec2p; p1c10n = cec2n;
					 } else if(p1c11p == 0 && p1c11n == 0) { p1c11p = cec2p; p1c11n = cec2n;
					 } else if(p1c12p == 0 && p1c12n == 0) { p1c12p = cec2p; p1c12n = cec2n;
					 } else if(p1c13p == 0 && p1c13n == 0) { p1c13p = cec2p; p1c13n = cec2n;
					 } else if(p1c14p == 0 && p1c14n == 0) { p1c14p = cec2p; p1c14n = cec2n;
					 } else if(p1c15p == 0 && p1c15n == 0) { p1c15p = cec2p; p1c15n = cec2n;
					 } else if(p1c16p == 0 && p1c16n == 0) { p1c16p = cec2p; p1c16n = cec2n;
					 } else if(p1c17p == 0 && p1c17n == 0) { p1c17p = cec2p; p1c17n = cec2n;
					 } else if(p1c18p == 0 && p1c18n == 0) { p1c18p = cec2p; p1c18n = cec2n;
					 } else if(p1c19p == 0 && p1c19n == 0) { p1c19p = cec2p; p1c19n = cec2n;
					 } else if(p1c20p == 0 && p1c20n == 0) { p1c20p = cec2p; p1c20n = cec2n;
					 } else if(p1c21p == 0 && p1c21n == 0) { p1c21p = cec2p; p1c21n = cec2n;
					 } else if(p1c22p == 0 && p1c22n == 0) { p1c22p = cec2p; p1c22n = cec2n;
					 } else if(p1c23p == 0 && p1c23n == 0) { p1c23p = cec2p; p1c23n = cec2n;
					 } else if(p1c24p == 0 && p1c24n == 0) { p1c24p = cec2p; p1c24n = cec2n;
					 }
					 cec2p=cec3p; cec2n=cec3n;
					 cec3p=cec4p; cec3n=cec4n;
					 cec4p=cec5p; cec4n=cec5n;
					 cec5p=cec6p; cec5n=cec6n;
					 cec6p=cec7p; cec6n=cec7n;
					 cec7p=cec8p; cec7n=cec8n;
					 cec8p=cec9p; cec8n=cec9n;
					 cec9p=cec10p; cec9n=cec10n;
					 cec10p=cec11p; cec10n=cec11n;
					 cec11p=cec12p; cec11n=cec12n;
					 cec12p=cec13p; cec12n=cec13n;
					 cec13p=cec14p; cec13n=cec14n;
					 cec14p=cec15p; cec14n=cec15n;
					 cec15p=cec16p; cec15n=cec16n;
					 cec16p=cec17p; cec16n=cec17n;
					 cec17p=cec18p; cec17n=cec18n;
					 cec18p=cec19p; cec18n=cec19n;
					 cec19p=cec20p; cec19n=cec20n;
					 cec20p=cec21p; cec20n=cec21n;
					 cec21p=cec22p; cec21n=cec22n;
					 cec22p=cec23p; cec22n=cec23n;
					 cec23p=cec24p; cec23n=cec24n;
				 }
				 
				 System.out.println("-------------------------------------");
				 System.out.println("p1 : " + p1c1p+p1c1n + " " + p1c2p+p1c2n + " " + p1c3p+p1c3n + " " + 
						 			p1c4p+p1c4n + " " + p1c5p+p1c5n + " " + p1c6p+p1c6n + " " + p1c7p+p1c7n + " " + 
						 			p1c8p+p1c8n + " " + p1c9p+p1c9n + " " + p1c10p+p1c10n + " " + p1c11p+p1c11n + " " + 
						 			p1c12p+p1c12n + " " + p1c13p+p1c13n + " " + p1c14p+p1c14n + " " + p1c15p+p1c15n + " " + 
						 			p1c16p+p1c16n + " " + p1c17p+p1c17n + " " + p1c18p+p1c18n + " " + p1c19p+p1c19n + " " + 
						 			p1c20p+p1c20n + " " + p1c21p+p1c21n + " " + p1c22p+p1c22n + " " + p1c23p+p1c23n + " " +
						 			p1c24p+p1c24n);
				 System.out.println("("+trun+"턴 P2)    " +cec1p+cec1n);
				 System.out.println("p2 : " + p2c1p+p2c1n + " " + p2c2p+p2c2n + " " + p2c3p+p2c3n + " " + 
				 			p2c4p+p2c4n + " " + p2c5p+p2c5n + " " + p2c6p+p2c6n + " " + p2c7p+p2c7n + " " + 
				 			p2c8p+p2c8n + " " + p2c9p+p2c9n + " " + p2c10p+p2c10n + " " + p2c11p+p2c11n + " " + 
				 			p2c12p+p2c12n + " " + p2c13p+p2c13n + " " + p2c14p+p2c14n + " " + p2c15p+p2c15n + " " + 
				 			p2c16p+p2c16n + " " + p2c17p+p2c17n + " " + p2c18p+p2c18n + " " + p2c19p+p2c19n + " " + 
				 			p2c20p+p2c20n + " " + p2c21p+p2c21n + " " + p2c22p+p2c22n + " " + p2c23p+p2c23n + " " +
				 			p2c24p+p2c24n);
				 System.out.println("-------------------------------------");
				//p2
				 boolean p2IsSame = false; //패턴이 존재하면 true, p2NumSame에 넘버 저장 
				 int p2NumSame = 0;
				 if(p2c1p == cec1p || p2c1n == cec1n) { p2IsSame=true; p2NumSame=1;}
				 else if (p2c2p == cec1p || p2c2n == cec1n) { p2IsSame=true; p2NumSame=2;}
				 else if (p2c3p == cec1p || p2c3n == cec1n) { p2IsSame=true; p2NumSame=3;}
				 else if (p2c4p == cec1p || p2c4n == cec1n) { p2IsSame=true; p2NumSame=4;}
				 else if (p2c5p == cec1p || p2c5n == cec1n) { p2IsSame=true; p2NumSame=5;}
				 else if (p2c6p == cec1p || p2c6n == cec1n) { p2IsSame=true; p2NumSame=6;}
				 else if (p2c7p == cec1p || p2c7n == cec1n) { p2IsSame=true; p2NumSame=7;}
				 else if (p2c8p == cec1p || p2c8n == cec1n) { p2IsSame=true; p2NumSame=8;}
				 else if (p2c9p == cec1p || p2c9n == cec1n) { p2IsSame=true; p2NumSame=9;}
				 else if (p2c10p == cec1p || p2c10n == cec1n) { p2IsSame=true; p2NumSame=10;}
				 else if (p2c11p == cec1p || p2c11n == cec1n) { p2IsSame=true; p2NumSame=11;}
				 else if (p2c12p == cec1p || p2c12n == cec1n) { p2IsSame=true; p2NumSame=12;}
				 else if (p2c13p == cec1p || p2c13n == cec1n) { p2IsSame=true; p2NumSame=13;}
				 else if (p2c14p == cec1p || p2c14n == cec1n) { p2IsSame=true; p2NumSame=14;}
				 else if (p2c15p == cec1p || p2c15n == cec1n) { p2IsSame=true; p2NumSame=15;}
				 else if (p2c16p == cec1p || p2c16n == cec1n) { p2IsSame=true; p2NumSame=16;}
				 else if (p2c17p == cec1p || p2c17n == cec1n) { p2IsSame=true; p2NumSame=17;}
				 else if (p2c18p == cec1p || p2c18n == cec1n) { p2IsSame=true; p2NumSame=18;}
				 else if (p2c19p == cec1p || p2c19n == cec1n) { p2IsSame=true; p2NumSame=19;}
				 else if (p2c20p == cec1p || p2c20n == cec1n) { p2IsSame=true; p2NumSame=20;}
				 else if (p2c21p == cec1p || p2c21n == cec1n) { p2IsSame=true; p2NumSame=21;}
				 else if (p2c22p == cec1p || p2c22n == cec1n) { p2IsSame=true; p2NumSame=22;}
				 else if (p2c23p == cec1p || p2c23n == cec1n) { p2IsSame=true; p2NumSame=23;}
				 else if (p2c24p == cec1p || p2c24n == cec1n) { p2IsSame=true; p2NumSame=24;}
				
				 if(p2IsSame) //패턴이 존재하면 비어있는 cec넘버 탐색해서 cec1을 저장하고, p2NumSame와 같은 넘버의 p2c를  cec1저장
				 {	
					 if(cec2p == 0 && cec2n == 0) { cec2p = cec1p; cec2n = cec1n;
					 }else if(cec3p == 0 && cec3n == 0) { cec3p = cec1p; cec3n = cec1n;
					 }else if(cec4p == 0 && cec4n == 0) { cec4p = cec1p; cec4n = cec1n;
					 }else if(cec5p == 0 && cec5n == 0) { cec5p = cec1p; cec5n = cec1n;
					 }else if(cec6p == 0 && cec7n == 0) { cec6p = cec1p; cec6n = cec1n;
					 }else if(cec7p == 0 && cec7n == 0) { cec7p = cec1p; cec7n = cec1n;
					 }else if(cec8p == 0 && cec8n == 0) { cec8p = cec1p; cec8n = cec1n;
					 }else if(cec9p == 0 && cec9n == 0) { cec9p = cec1p; cec9n = cec1n;
					 }else if(cec10p == 0 && cec10n == 0) { cec10p = cec1p; cec10n = cec1n;
					 }else if(cec11p == 0 && cec11n == 0) { cec11p = cec1p; cec11n = cec1n;
					 }else if(cec12p == 0 && cec12n == 0) { cec12p = cec1p; cec12n = cec1n;
					 }else if(cec13p == 0 && cec13n == 0) { cec13p = cec1p; cec13n = cec1n;
					 }else if(cec14p == 0 && cec14n == 0) { cec14p = cec1p; cec14n = cec1n;
					 }else if(cec15p == 0 && cec15n == 0) { cec15p = cec1p; cec15n = cec1n;
					 }else if(cec16p == 0 && cec16n == 0) { cec16p = cec1p; cec16n = cec1n;
					 }else if(cec17p == 0 && cec17n == 0) { cec17p = cec1p; cec17n = cec1n;
					 }else if(cec18p == 0 && cec18n == 0) { cec18p = cec1p; cec18n = cec1n;
					 }else if(cec19p == 0 && cec19n == 0) { cec19p = cec1p; cec19n = cec1n;
					 }else if(cec20p == 0 && cec20n == 0) { cec20p = cec1p; cec20n = cec1n;
					 }else if(cec21p == 0 && cec21n == 0) { cec21p = cec1p; cec21n = cec1n;
					 }else if(cec22p == 0 && cec22n == 0) {	cec22p = cec1p; cec22n = cec1n;
					 }else if(cec23p == 0 && cec23n == 0) { cec23p = cec1p; cec23n = cec1n;
					 }else if(cec24p == 0 && cec24n == 0) { cec24p = cec1p; cec24n = cec1n;
					 }
					 
					 if(p2NumSame == 1) { cec1p = p2c1p; cec1n = p2c1n; p2c1p = 0; p2c1n =0; //p2c를 cec1에 저장 및 비우기
					 }else if (p2NumSame == 2) { cec1p = p2c2p; cec1n = p2c2n; p2c2p = 0; p2c2n =0; 
					 }else if (p2NumSame == 3) { cec1p = p2c3p; cec1n = p2c3n; p2c3p = 0; p2c3n =0; 
					 }else if (p2NumSame == 4) { cec1p = p2c4p; cec1n = p2c4n; p2c4p = 0; p2c4n =0; 
					 }else if (p2NumSame == 5) { cec1p = p2c5p; cec1n = p2c5n; p2c5p = 0; p2c5n =0; 
					 }else if (p2NumSame == 6) { cec1p = p2c6p; cec1n = p2c6n; p2c6p = 0; p2c6n =0; 
					 }else if (p2NumSame == 7) { cec1p = p2c7p; cec1n = p2c7n; p2c7p = 0; p2c7n =0; 
					 }else if (p2NumSame == 8) { cec1p = p2c8p; cec1n = p2c8n; p2c8p = 0; p2c8n =0; 
					 }else if (p2NumSame == 9) { cec1p = p2c9p; cec1n = p2c9n; p2c9p = 0; p2c9n =0; 
					 }else if (p2NumSame == 10) { cec1p = p2c10p; cec1n = p2c10n; p2c10p = 0;p2c10n =0; 
					 }else if (p2NumSame == 11) { cec1p = p2c11p; cec1n = p2c11n; p2c11p = 0; p2c11n =0; 
					 }else if (p2NumSame == 12) { cec1p = p2c12p; cec1n = p2c12n; p2c12p = 0; p2c12n =0; 
					 }else if (p2NumSame == 13) { cec1p = p2c13p; cec1n = p2c13n; p2c13p = 0; p2c13n =0; 
					 }else if (p2NumSame == 14) { cec1p = p2c14p; cec1n = p2c14n; p2c14p = 0; p2c14n =0; 
					 }else if (p2NumSame == 15) { cec1p = p2c15p; cec1n = p2c15n; p2c15p = 0; p2c15n =0; 
					 }else if (p2NumSame == 16) { cec1p = p2c16p; cec1n = p2c16n; p2c16p = 0; p2c16n =0; 
					 }else if (p2NumSame == 17) { cec1p = p2c17p; cec1n = p2c17n; p2c17p = 0; p2c17n =0; 
					 }else if (p2NumSame == 18) { cec1p = p2c18p; cec1n = p2c18n; p2c18p = 0; p2c18n =0; 
					 }else if (p2NumSame == 19) { cec1p = p2c19p; cec1n = p2c19n; p2c19p = 0; p2c19n =0; 
					 }else if (p2NumSame == 20) { cec1p = p2c20p; cec1n = p2c20n; p2c20p = 0; p2c20n =0; 
					 }else if (p2NumSame == 21) { cec1p = p2c21p; cec1n = p2c21n; p2c21p = 0; p2c21n =0; 
					 }else if (p2NumSame == 22) { cec1p = p2c22p; cec1n = p2c22n; p2c22p = 0; p2c22n =0; 
					 }else if (p2NumSame == 23) { cec1p = p2c23p; cec1n = p2c23n; p2c23p = 0; p2c23n =0; 
					 }else if (p2NumSame == 24) { cec1p = p2c24p; cec1n = p2c24n; p2c24p = 0; p2c24n =0; 
					 }

					 //카드를 제출하고, 카드가 없으면 종료함(1223)
					 if(p1c1p==0 && p1c1n==0 && p1c2p==0 && p1c2n==0 && p1c3p==0 && p1c3n==0 &&
						p1c4p==0 && p1c4n==0 && p1c5p==0 && p1c5n==0 && p1c6p==0 && p1c6n==0 && 
						p1c7p==0 && p1c7n==0 && p1c8p==0 && p1c8n==0 && p1c9p==0 && p1c9n==0 && 
						p1c10p==0 && p1c10n==0 && p1c11p==0 && p1c11n==0 && p1c12p==0 && p1c12n==0 && 
						p1c13p==0 && p1c13n==0 && p1c14p==0 && p1c14p==0 && p1c15p==0 && p1c15n==0 && 
						p1c16p==0 && p1c16n==0 && p1c17p==0 && p1c17p==0 && p1c18p==0 && p1c18n==0 && 
						p1c19p==0 && p1c19n==0 && p1c20p==0 && p1c20p==0 && p1c21p==0 && p1c21n==0 && 
						p1c22p==0 && p1c22n==0 && p1c23p==0 && p1c23n==0 && p1c24p==0 && p1c24n==0) {
						 winner=2;
						 continue;
					 }
				 }else { //(2)만약 p2의 모든 패중에 일치하는 p or n 이 없으면, 비어있는 p2c에 cec2삽입 , (n:2>23)cec(n) = cec(n+1) 다 대입
					 if(p2c1p == 0 && p2c1n == 0) { p2c1p = cec2p; p2c1n = cec2n;
					 } else if(p2c2p == 0 && p2c2n == 0) { p2c2p = cec2p; p2c2n = cec2n;
					 } else if(p2c3p == 0 && p2c3n == 0) { p2c3p = cec2p; p2c3n = cec2n;
					 } else if(p2c4p == 0 && p2c4n == 0) { p2c4p = cec2p; p2c4n = cec2n;
					 } else if(p2c5p == 0 && p2c5n == 0) { p2c5p = cec2p; p2c5n = cec2n;
					 } else if(p2c6p == 0 && p2c6n == 0) { p2c6p = cec2p; p2c6n = cec2n;
					 } else if(p2c7p == 0 && p2c7n == 0) { p2c7p = cec2p; p2c7n = cec2n;
					 } else if(p2c8p == 0 && p2c8n == 0) { p2c8p = cec2p; p2c8n = cec2n;
					 } else if(p2c9p == 0 && p2c9n == 0) { p2c9p = cec2p; p2c9n = cec2n;
					 } else if(p2c10p == 0 && p2c10n == 0) { p2c10p = cec2p; p2c10n = cec2n;
					 } else if(p2c11p == 0 && p2c11n == 0) { p2c11p = cec2p; p2c11n = cec2n;
					 } else if(p2c12p == 0 && p2c12n == 0) { p2c12p = cec2p; p2c12n = cec2n;
					 } else if(p2c13p == 0 && p2c13n == 0) { p2c13p = cec2p; p2c13n = cec2n;
					 } else if(p2c14p == 0 && p2c14n == 0) { p2c14p = cec2p; p2c14n = cec2n;
					 } else if(p2c15p == 0 && p2c15n == 0) { p2c15p = cec2p; p2c15n = cec2n;
					 } else if(p2c16p == 0 && p2c16n == 0) { p2c16p = cec2p; p2c16n = cec2n;
					 } else if(p2c17p == 0 && p2c17n == 0) { p2c17p = cec2p; p2c17n = cec2n;
					 } else if(p2c18p == 0 && p2c18n == 0) { p2c18p = cec2p; p2c18n = cec2n;
					 } else if(p2c19p == 0 && p2c19n == 0) { p2c19p = cec2p; p2c19n = cec2n;
					 } else if(p2c20p == 0 && p2c20n == 0) { p2c20p = cec2p; p2c20n = cec2n;
					 } else if(p2c21p == 0 && p2c21n == 0) { p2c21p = cec2p; p2c21n = cec2n;
					 } else if(p2c22p == 0 && p2c22n == 0) { p2c22p = cec2p; p2c22n = cec2n;
					 } else if(p2c23p == 0 && p2c23n == 0) { p2c23p = cec2p; p2c23n = cec2n;
					 } else if(p2c24p == 0 && p2c24n == 0) { p2c24p = cec2p; p2c24n = cec2n;
					 }
					 cec2p=cec3p; cec2n=cec3n;
					 cec3p=cec4p; cec3n=cec4n;
					 cec4p=cec5p; cec4n=cec5n;
					 cec5p=cec6p; cec5n=cec6n;
					 cec6p=cec7p; cec6n=cec7n;
					 cec7p=cec8p; cec7n=cec8n;
					 cec8p=cec9p; cec8n=cec9n;
					 cec9p=cec10p; cec9n=cec10n;
					 cec10p=cec11p; cec10n=cec11n;
					 cec11p=cec12p; cec11n=cec12n;
					 cec12p=cec13p; cec12n=cec13n;
					 cec13p=cec14p; cec13n=cec14n;
					 cec14p=cec15p; cec14n=cec15n;
					 cec15p=cec16p; cec15n=cec16n;
					 cec16p=cec17p; cec16n=cec17n;
					 cec17p=cec18p; cec17n=cec18n;
					 cec18p=cec19p; cec18n=cec19n;
					 cec19p=cec20p; cec19n=cec20n;
					 cec20p=cec21p; cec20n=cec21n;
					 cec21p=cec22p; cec21n=cec22n;
					 cec22p=cec23p; cec22n=cec23n;
					 cec23p=cec24p; cec23n=cec24n;
				 }
				 
			 }//for 끝
		 }
		 
		 
		 
		 
		
	}
}
