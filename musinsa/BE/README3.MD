### 구현 범위 설명



조회(search) 와 상품(product) 개념을 기반으로 패키지를 나누었습니다.

구현1~3 까지의 기능은 search 가 담당합니다

구현4의 기능은 product가 담당합니다.

![image-20240718001835053](https://github.com/user-attachments/assets/8ee2a0da-689b-4e8f-a596-e8bfca676672)


<br>

주요 패키지 구조는 `api` `controller` `domain` `facade`, `service` `infrastructure` 를 구성하고 있습니다.

![image-20240718002400733](https://github.com/user-attachments/assets/89e23b37-941c-4b05-81f1-5d4458ea221e)

api는 외부에 공개되는 api 담당,

controller는 내부 페이지 담당,

facade는 service의 복잡한 프로세스를 단순화하여 제공하는 담당

service, domain은 비즈니스 로직 담당

infrastructure은 db관련 담당으로

역할 구분 하였습니다.





<br>

데이터가 추후에는 대용량이며, 카테고리 별로 고유한 필드값을 가질것으로 판단했으며

조회 쿼리의 단순화와 단일 테이블시 부하를 분산하고자

카테고리 별로 분리하여 테이블을 설계했습니다.

![image-20240718001924995](https://github.com/user-attachments/assets/d7b16f06-0382-4db0-a68b-bca48d316870)


<br>

구현1~3은 가격을 중점으로 조회하는 API들입니다.

주로 가격을 중점으로 필터링 되고, 데이터 조회>데이터 변경  가치가 크다 생각했기 때문에 Trade off 하여 `가격(price)`을 index로 구성했습니다.

![image-20240718002149446](https://github.com/user-attachments/assets/b095794a-4ee6-4ef9-be80-7d87ed6b9bf0)





`구현 4에 관한 API 내용입니다.`

외부 제공 API 는 ProductApi 이며 구현4 에 관한 API 입니다.

Frontend 페이지에서 확인 할 수 있도록 ProductController를 추가로 구성하였습니다.



![image-20240718003525997](https://github.com/user-attachments/assets/f323df0e-73c6-420a-a2dd-ea6d5edc0fe3)



`구현 1~3에 관한 API 내용입니다.`

외부 제공 API 는 SearchApi이며 구현1~3 에 관한 API 입니다.

Frontend 페이지에서 확인 할 수 있도록 SearchController를 추가로 구성하였습니다.

![image-20240718003805471](https://github.com/user-attachments/assets/169303ef-89ad-47ab-b3fa-ca0523286a91)



<br>

카테고리 별로 테이블이 분리되었기 때문에 모든 카테고리 조회시에는 8개의 쿼리가 실행됩니다.

1건 조회 당, 8번의 쿼리는 부담이 될 수 있다 판단했고, `캐시`를 적용하여 이를 보완하고자 했습니다.



![image-20240718004112752](https://github.com/user-attachments/assets/0014055b-c25f-40e0-8b86-e1b5d0b06e92)


![image-20240718004829144](https://github.com/user-attachments/assets/e4eaba94-0943-466e-a690-1c02a4152185)

<BR>

`API 실패 시, 실패값과 실패 사유`를 클라이언트에게 알려줍니다.

exception과 request는 이를 담당합니다.

![image-20240718005145608](https://github.com/user-attachments/assets/e0c559a6-005e-47a4-98bf-cbb2313846fe)

![image-20240718005259865](https://github.com/user-attachments/assets/57021ab0-732c-49d6-a084-6ca90ef5c4eb)

![image-20240718005311474](https://github.com/user-attachments/assets/bc5630be-2b10-4841-aeba-158db991b2e2)

<br>

구현한 API를 간단하게 확인 할 수 있도록 FrontEnd 페이지를 구성했습니다.

![image-20240718005541794](https://github.com/user-attachments/assets/eebc7ca6-c8ae-4d04-95f2-a141bd2cdd11)

<br>

Unit test 및 Integration test 코드를 구성했습니다.

![image-20240718005656011](https://github.com/user-attachments/assets/2399fc28-5322-4c51-ab84-424df44d8e1d)



## 코드 빌드/테스트/실행방법

`open jdk 17` 이상으로 jdk를 먼저 세팅해주세요.



**코드 빌드**

```shell
$ ./gradlew build
```



**코드 테스트**

```
$ ./gradlew test
```



**실행 방법**

```
$ java -jar --Spring.profiles.active=dev build/libs/musinsa-app.jar 
```

