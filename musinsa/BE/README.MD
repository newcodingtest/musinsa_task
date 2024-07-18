### 구현 범위 설명
체크리스트 모두 구현되어 있습니다.
<br>
##### 과제
- [x] 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API

- [x]  단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 -판매하는 브랜드와 카테고리의     상 품가격, 총액을 조회하는 API

- [x] 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API

- [x] 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API

##### 가산점
- [x] Unit test 및 Integration test 작성

- [x] Frontend 페이지 구현

<br><br><br>
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
<br><br><br>

## 기타
### 가격을 인덱스로 설정한 이유

#### 가격 필드를 인덱스로 설정하는 경우

1. **빈번한 가격 기반 조회**: 가격을 기준으로 데이터를 자주 조회하거나 정렬할 경우.
2. **가격 필터링**: 가격 범위로 데이터를 자주 필터링할 경우.
3. **성능 최적화 필요**: 가격 기반 쿼리의 성능이 중요한 경우.



<b>가격을 인덱스로 설정하면 다음과 같은 이점이 있다.</b>

- **빠른 조회**: 가격 순서로 정렬된 데이터를 빠르게 조회.
- **효율적 필터링**: 특정 가격 범위 내의 데이터를 빠르게 필터링.


#### 인덱스의 단점

1. **추가 저장 공간 필요**: 인덱스를 저장하기 위한 추가 공간이 필요하다.
2. **데이터 삽입/수정 시 성능 저하**: 인덱스를 유지 관리하기 위해 삽입, 삭제, 업데이트 작업 시 오버헤드가 발생한다.



### 결론

대용량 환경이라 가정하고 가격 기반으로 조회 하는것이 주된 중요 기능이라 생각했다.
때문에 장단점을 trade Off 하여 인덱스를 결정하였다.




<br>

### 가격에  BigDecimal 사용이유

### 1. **정확한 소수점 연산**

`BigDecimal`은 소수점 이하의 연산을 정확하게 처리할 수 있다. 부동소수점 타입인 `float`나 `double`은 근사값을 저장하므로, 특히 금융 계산에서 부정확한 결과를 초래할 수 있다.

예시:

```
System.out.println(0.1 + 0.2); // 0.30000000000000004
System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2"))); // 0.3
```

### 2. **정확한 값 표현**

`BigDecimal`은 정수부와 소수부를 분리하여 저장하기 때문에, 입력한 값을 정확하게 표현할 수 있다. 반면, `float`나 `double`은 내부적으로 2진수로 저장되기 때문에 일부 10진수를 정확하게 표현하지 못할 수 있습니다.

### 3. **금융 계산에 적합**

금융 계산에서는 센트 단위의 작은 오차도 큰 문제가 될 수 있다. `BigDecimal`을 사용하면 이러한 오차 없이 정확한 계산이 가능하다.

### 4. **스케일과 정밀도 제어**

`BigDecimal`은 소수점 이하 자리수를 지정할 수 있는 `scale`과 전체 자리수를 지정할 수 있는 `precision`을 통해 유연하게 값의 정밀도를 제어할 수 있다.



<br><br>





## 코드 빌드/테스트/실행방법

`open jdk 17` 이상으로 jdk를 먼저 세팅해주세요.


### 프로젝트 클론
```shell
$ git init

$ git clone https://github.com/newcodingtest/musinsa_task.git
```

### 코드 빌드 폴더 이동
```shell
$ cd musinsa_task/musinsa/BE
```

### 코드 빌드

```shell
$ gradlew build
```


### 코드 테스트

```shell
$ gradlew test
```

<br>

### 실행 방법

```shell
$ cd build/libs

$ java -jar musinsa-app.jar --Spring.profiles.active=dev

```
DB는 h2db 이며 접속 아래는 접속정보
이다
```shell
http://localhost:1004/h2-console

jdbc url: jdbc:h2:mem:devdb
id: sa
pw: password
```
![image](https://github.com/user-attachments/assets/377cd587-3a37-4894-be38-06361d6ebaa2)
<br>

### 화면으로 과제 기능 확인하기
```aidl
http://localhost:1004
```
구현 별로 확인 할 수 있는 간단한 홈 화면 입니다.
![image](https://github.com/user-attachments/assets/7f24b441-c61a-409f-9197-4fd9b1ec8c12)

<br>
[카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API] 클릭시
해당 기능에 대한 결과를 확인 할 수 있습니다.

![image](https://github.com/user-attachments/assets/5decbab2-dc2b-4709-9965-fbeb9e7756d4)

<br>
[단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
                조회하는 API] 클릭시
해당 기능에 대한 결과를 확인 할 수 있습니다.

![image](https://github.com/user-attachments/assets/a68a667a-dd6c-42f1-8e9c-e632b50e57bf)

<br>
[카테고리 이름으로 최저/최고 브랜드 및 상품 가격 조회 API] 는 하위 카테고리 별로 버튼을 클릭하면 확인 할 수 있습니다.

![image](https://github.com/user-attachments/assets/058b7586-a811-4484-83a1-9b10d8fbf8a3)

![image](https://github.com/user-attachments/assets/075eeb8e-e268-418d-84c3-6878901c1e36)
![image](https://github.com/user-attachments/assets/d57d347e-7d2d-405b-97ab-ae1bd0d16de2)
<br><br>

[브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API]를 클릭하면 Create, Update, Delete 할 수 있는 화면으로 이동합니다.
![image](https://github.com/user-attachments/assets/6f27a5f8-b326-4404-a4df-2a6638ecad0c)
![image](https://github.com/user-attachments/assets/5094b510-de58-40bc-b1b8-6a7d372f460e)
## API 명세

![스크린샷 2024-07-17 151447](https://github.com/user-attachments/assets/08637c43-f46a-4e02-8640-071c101e86d4)

```
http://localhost:1004/api/cheapest-category
```
Response
```
{
    "products": [
        {
            "카테고리": "모자",
            "브랜드": "D",
            "가격": "1,500"
        },
        {
            "카테고리": "상의",
            "브랜드": "C",
            "가격": "10,000"
        },
        {
            "카테고리": "아우터",
            "브랜드": "E",
            "가격": "5,000"
        },
        {
            "카테고리": "바지",
            "브랜드": "D",
            "가격": "3,000"
        },
        {
            "카테고리": "스니커즈",
            "브랜드": "G",
            "가격": "9,000"
        },
        {
            "카테고리": "가방",
            "브랜드": "A",
            "가격": "2,000"
        },
        {
            "카테고리": "양말",
            "브랜드": "I",
            "가격": "1,700"
        },
        {
            "카테고리": "액세서리",
            "브랜드": "F",
            "가격": "1,900"
        }
    ],
    "총액": "34,100"
}
```
<br><br>

![스크린샷 2024-07-17 151459](https://github.com/user-attachments/assets/fb987a28-3a1c-431c-befd-eb044717a48c)

```
http://localhost:1004/api/cheapest-brand
```
Response
```
{
    "최저가": {
        "브랜드": "D",
        "카테고리": [
            {
                "카테고리": "모자",
                "가격": "1,500"
            },
            {
                "카테고리": "상의",
                "가격": "10,100"
            },
            {
                "카테고리": "아우터",
                "가격": "5,100"
            },
            {
                "카테고리": "바지",
                "가격": "3,000"
            },
            {
                "카테고리": "양말",
                "가격": "2,400"
            },
            {
                "카테고리": "스니커즈",
                "가격": "9,500"
            },
            {
                "카테고리": "액세서리",
                "가격": "2,000"
            },
            {
                "카테고리": "가방",
                "가격": "2,500"
            }
        ]
    },
    "총액": "36,100"
}
```

<br><br>

![스크린샷 2024-07-17 151516](https://github.com/user-attachments/assets/59fe89f1-490c-4511-af68-0e0d9df31265)
```
http://localhost:1004/api/cheapest-hightest/${category}


${category}:
top, outer, bottom, socks, sneakers, hat, bag, accessory
```
Response
```
{
    "카테고리": "상의",
    "최저가": {
        "브랜드": "C",
        "가격": "10,000"
    },
    "최고가": {
        "브랜드": "I",
        "가격": "11,400"
    }
}
```
<br><br>


![스크린샷 2024-07-17 151532](https://github.com/user-attachments/assets/3a6e281b-8581-4131-b755-aea8fa63538e)

```
http://localhost:1004/api/products
```
**Request Body** (json)

```
{
    "brand" : "C",       //A~Z
    "category" : "top",  //top, outer, bottom, socks, sneakers, hat, bag, accessory
    "price" : 10000
}
```
Response
```
{
    "카테고리": "상의",
    "최저가": {
        "브랜드": "C",
        "가격": "10,000"
    },
    "최고가": {
        "브랜드": "I",
        "가격": "11,400"
    }
}
```

<br><br>

![스크린샷 2024-07-17 151544](https://github.com/user-attachments/assets/ecabe12b-0f18-4b2c-ae9a-30cf813203f6)

```
http://localhost:1004/api/products
```

**Request Body** (json)
```
{
    "id" : 10,    
    "brand" : "C",      //A~Z
    "category" : "top", //top, outer, bottom, socks, sneakers, hat, bag, accessory
    "price" : 90000
}
```
Response
```
HTTP Status 200
```
<br><br>

![스크린샷 2024-07-17 151558](https://github.com/user-attachments/assets/2cfbb6dc-1135-4fec-9322-496400162262)

```
http://localhost:1004/api/products
```

**Request Body** (json)
```
{
    "id" : 10,
    "category" : "top" //top, outer, bottom, socks, sneakers, hat, bag, accessory
}
```
Response
```
HTTP Status 200
```
