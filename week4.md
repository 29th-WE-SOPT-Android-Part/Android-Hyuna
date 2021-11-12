# Android Assignment week 4

## level 1 필수 과제

### 실행 화면

📌 Postman sign up

![image](https://user-images.githubusercontent.com/63237214/141426551-72053f99-c17d-4397-853c-4908b97edfae.png)

</br>

📌 Postman log in

![image](https://user-images.githubusercontent.com/63237214/141426680-1a909fc1-aca5-4dd9-8c7e-0d2425031c05.png)

</br>

📌 로그인

![image](https://user-images.githubusercontent.com/63237214/141430068-77c8b65b-c97b-4567-a9e0-aeb9e45b83f3.png)![image-20211112165009116](C:\Users\HYUNA\AppData\Roaming\Typora\typora-user-images\image-20211112165009116.png)

</br>

📌 회원가입

![image](https://user-images.githubusercontent.com/63237214/141429827-2d80c816-05bd-4880-96c8-e886e71e1628.png)![image](https://user-images.githubusercontent.com/63237214/141429956-cf775043-2883-49ed-bcec-2483f9e232e0.png)

</br>

</br>

------

### 구현 로직

📌 build.gradle

```kotlin
dependencies {

    //서버 연결을 위한 Retrofit2
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    //Retrofit2에서 gson 사용을 위한 컨버터
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    //gson
    implementation "com.google.code.gson:gson:2.8.6"
...
}
```

</br>

📌 AndroidManifest.xml

```kotlin
//인터넷 접속 권한 부여
<uses-permission android:name="android.permission.INTERNET"/>

//http로 통신하기 위해 해당 속성 true로
<application
        android:usesCleartextTraffic="true"
```

</br>

📌RequestSigninData.kt

```kotlin
//로그인 서버 request 객체 설계
data class RequestSigninData(
    @SerializedName("email")
    val email: String,
    val password: String
)
```

</br>

📌RequestSignupData.kt

```kotlin
//회원가입 서버 request 객체 설계
data class RequestSignupData(
    val name: String,
    val email: String,
    val password: String
)
```

</br>

📌ResponseSigninData.kt

```kotlin
//로그인 서버 response 객체 설계, 회원가입의 경우도 아래와 같음
data class ResponseSigninData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Data
) {
    data class Data(
        val id : Int,
        val name : String,
        val email : String
    )
}
```

</br>

📌SampleService.kt

```kotlin
//Retrofit Interface 설계
interface SampleService {
    @Headers("Content-Type:application/json") //어노테이션 이용해 헤더값 넣어주기
    @POST("user/login") //로그인에 맞는 HTTP 메소드를 설정해주고 API의 URI 작성

    fun postLogin(
        @Body body : RequestSigninData //어노테이션 이용해 데이터 넣어주기
    ) : Call<ResponseSigninData>

    @Headers("Content-Type:application/json")
    @POST("user/signup")

    fun postSignup(
        @Body body : RequestSignupData
    ) : Call<ResponseSignupData>
}
```

</br>

📌ServiceCreator.kt

```kotlin
//Retrofit Interface 실제 구현체(객체)
object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/" //메인 서버 도메인

    private val retrofit : Retrofit = Retrofit
        .Builder() //레트로핏 빌더 생성
        .baseUrl(BASE_URL) //빌더 객체의 baseURL 호출. 서버의 메인 URL 전달
        .addConverterFactory(GsonConverterFactory.create()) //gson 컨버터 연동
        .build() //Retrofit 객체 반환

    val SAMPLE_SERVICE: SampleService = retrofit.create(SampleService::class.java) //인터페이스 객체를 create에 넘겨 실제 구현체 생성
} 
```

</br>

📌SignInActivity.kt

```kotlin
private fun initNetwork() {
    //서버에 요청을 보내기 위한 RequestData 생성
    val requestSigninData = RequestSigninData(
        email = binding.idInput.text.toString(),
        password = binding.pwInput.text.toString()
    )
    
    //Retrofit이 만들어준 Interface 구현체에 접근하여 Call 객체를 받아옴
    val call: Call<ResponseSigninData> = ServiceCreator.SAMPLE_SERVICE.postLogin(requestSigninData)

    //call 객체에 enqueue를 호출하여 실제 서버 통신을 비동기적으로 요청
    call.enqueue(object : Callback<ResponseSigninData> {
        override fun onResponse( //Callback 익명 클래스 선언
            call: Call<ResponseSigninData>,
            response: Response<ResponseSigninData>
        ) {
            if(response.isSuccessful) { //Status Code가 200~300 사이일 때 response.isSuccessful이 true 반환
                Toast.makeText(this@SignInActivity,"${response.body()?.data?.name}님 반갑습니다",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            }
            else {
                Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }

        //response.isSuccessful이 false이거나 body()에 값이 없을 경우 에러처리
        override fun onFailure(call: Call<ResponseSigninData>, t: Throwable) {
			Log.e("NetworkTest", "error:$t")
        }
    })
}
```

</br>

📌SignUpActivity.kt

```kotlin
private fun initNetwork() {
    //서버에 요청을 보내기 위한 RequestData 생성
        val requestSignupData = RequestSignupData(
            name = binding.nameInputSignup.text.toString(),
            email = binding.idInputSignup.text.toString(),
            password = binding.pwInputSignup.text.toString()
        )

    //Retrofit이 만들어준 Interface 구현체에 접근하여 Call 객체를 받아옴
        val call: Call<ResponseSignupData> = ServiceCreator.SAMPLE_SERVICE.postSignup(requestSignupData)

        call.enqueue(object : Callback<ResponseSignupData> { //call 객체에 enqueue를 호출하여 실제 서버 통신을 비동기적으로 요청
            override fun onResponse( //Callback 익명 클래스 선언
                call: Call<ResponseSignupData>,
                response: Response<ResponseSignupData>
            ) {
                if(response.isSuccessful) { //Status Code가 200~300 사이일 때 response.isSuccessful이 true 반환
                    Toast.makeText(this@SignUpActivity,"${response.body()?.data?.name}님 회원가입 완료되었습니다",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                    finish()
                }
                else {
                    Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }

            //response.isSuccessful이 false이거나 body()에 값이 없을 경우 에러처리
            override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
```

</br>

</br>

------

### 배운 것

**@SerializedName()**

대부분은 세미나에서 다뤄본 내용이라 무탈하게 넘어갔는데 회원가입에서 완료 후 토스트메세지를 띄우도록 했음에도 불구하고 메세지가 뜨지 않았다. intent는 제대로 작동하니 토스트 메세지만 문제인 것 같아서 토스트 메세지 코드를 뜯어봤다.

📌SignUpActivity.kt

```kotlin
val call: Call<ResponseSignupData> = ServiceCreator.SAMPLE_SERVICE.postSignup(requestSignupData)

call.enqueue(object : Callback<ResponseSignupData> {
    override fun onResponse(
        call: Call<ResponseSignupData>,
        response: Response<ResponseSignupData>
    ) {
        if(response.isSuccessful) {
            Toast.makeText(this@SignUpActivity,"${response.body()?.data?.name}님 회원가입 완료되었습니다",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
            finish()
        }
        else {
            Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
        }
    }
```

response를 통해 이름 정보를 받아오니 response 변수를 다시 봤고, response 변수는 위에서 call 객체를 받아온 것과 관련 있으니 결론적으로 requestSignupData를 봐야겠다는 결론에 이르렀다(?)

📌RequestSignupData.kt

```kotlin
data class RequestSignupData(
    @SerializedName("email")
    val name: String,
    val email: String,
    val password: String
)
```

처음에는 RequestSignupData.kt 파일 코드가 위와 같았다. 아마 RequestSigninData.kt 를 복제해서 name 값만 새로 넣어주느라 그렇게 된 것 같다. 근데 간과한 사실이 name값이 추가됨으로써 email도 name으로, 원래 name도 name으로... ^__^

📌RequestSignupData.kt

```kotlin
data class RequestSignupData(
    val name: String,
    val email: String,
    val password: String
)
```

그래서 @SerializedName("email")을 지우고 실행해보니 회원가입이 완료되었음을 알리는 토스트 메세지가 정상적으로 작동했다. 

아직 모든 코드를 완벽히 이해하고 있지는 않아서 확실한 결론은 아니지만 어떤 name값을 받아와야 할 지 몰라서 토스트 메세지를 띄울 수 없었던 게 아닐까... 생각해본다.

























