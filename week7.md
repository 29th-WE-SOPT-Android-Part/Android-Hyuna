# Android Assignment week 7

## level 1 필수 과제 (1)  온보딩 화면 만들기

### 실행 화면

<img width="30%" src="https://user-images.githubusercontent.com/63237214/146556581-2ab3b171-b1da-4954-93c6-4e1338bdd1ec.gif"/>

### 구현 로직

<img width="60%" alt="image" src="https://user-images.githubusercontent.com/63237214/146556948-736f63b4-9a15-4819-888b-ddf59c886bf5.png">

📌 onboardingFragment1.kt

```kotlin
class onboardingFragment1 : Fragment() {
    private var _binding : FragmentOnboarding1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboarding1Binding.inflate(inflater)

        binding.btnNext.setOnClickListener{ // 다음으로 버튼에 클릭리스너 설정
            findNavController().navigate(R.id.action_onboardingFragment1_to_onboardingFragment2)
        } //findNavController 이용하여 첫번째 페이지에서 두번째 페이지로 넘어가는 액션을 걸어줌

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
```

onboardingFragment2.kt 도 유사하게 작성



📌 onboardingFragment1.kt

```kotlin
class onboardingFragment3 : Fragment() {

    private var _binding : FragmentOnboarding3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboarding3Binding.inflate(inflater)

        binding.btnStart.setOnClickListener{ // 시작하기 버튼에 클릭리스너 설정
            val intent = Intent(requireActivity(), SignInActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        } // 액티비티 intent 이용해 로그인 액티비티로 전환되도록 구현

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```



📌 androidmanifest.xml

```xml
<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.AndroidAssignment1"
        android:usesCleartextTraffic="true">
        <activity
            android:name=".ui.view.SignInActivity"
            android:exported="false" />
        <activity
            android:name=".ui.view.OnboardingActivity" // 온보딩 액티비티를 시작 페이지로 설정
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".ui.view.SignUpActivity" />
        <activity android:name=".ui.view.MainActivity" />
    </application>
```



------



## level 1 필수 과제 (2)  SharedPreferences 활용해서 자동로그인(및 해제) 구현

### 실행 화면

<img width="30%" src="https://user-images.githubusercontent.com/63237214/146558667-2df5ce44-61ad-4128-beda-9eeefb4a51a2.gif"/>

### 구현 로직

📌 SignInActivity.kt

SignInActivity에 자동로그인 버튼을 추가하여 해당 버튼에 클릭리스너 설정해줌

```kotlin
private fun initClickEvent(){
        binding.btnAutoLogin.setOnClickListener{ // 버튼 클릭시 버튼 상태 전환
            binding.btnAutoLogin.isSelected = !binding.btnAutoLogin.isSelected

            SOPTSharedPreferences.setAutoLogin(this, binding.btnAutoLogin.isSelected)
        }
    }

    private fun isAutoLogin(){ // 자동로그인시 토스트메세지 띄우고 바로 메인 액티비티로 화면 전환
        if(SOPTSharedPreferences.getAutoLogin(this)) {
            shortToast("자동로그인 완료")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
```



📌 ProfileFragment.kt

ProfileFragment에 환경설정 버튼을 추가하여 해당 버튼에 클릭리스너 설정해줌

```kotlin
private fun clickSetting() { 
        binding.btnSetting.setOnClickListener { // 버튼 클릭시 환경설정 액티비티로 화면 전환
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }
    }
```



📌 SettingActivity.kt

SettingActivity를 추가하여 자동로그인 해제 버튼에 클릭리스너 설정해줌

```kotlin
private fun clickAutoLogin() {
    binding.btnAutoLogin.setOnClickListener { // 버튼 클릭시 자동로그인 관련 저장된 값 삭제
        SOPTSharedPreferences.removeAutoLogin(this,true)
        SOPTSharedPreferences.clearAutoLogin(this, true)
    }
}
```



------



## level 1 필수 과제 (3)  Util 클래스 코드 및 패키징 방식

📌 Util 클래스 코드

```kotlin
object ViewExt{
    fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
```

토스트 메세지 띄우는 함수를 간결화하기 위해 ViewExt 파일에 따로 작성하여 Util 패키지에 넣어둠



📌 패키징 방식

<img width="30%" src="https://user-images.githubusercontent.com/63237214/146560059-74b95e38-81bf-4ece-9aff-11aa6543ff7b.png"/>

1. data
   local 패키지에는 로컬 데이터와 관련된 data 파일들을 모아둠
   remote 패키지에는 서버 연동과 관련된 request data, response data, service creator 등의 파일을 모아둠
2. ui
   apdapter 패키지에는 adapter 파일들을 모아둠
   view 패키지에는 view와 관련된 activity와 fragment 파일들을 모아둠
3. util
   SharedPreferences 로직이 있는 SOPTSharedPreferences 파일과 토스트 함수 관련 로직이 있는 ViewExt 파일을 모아둠



------



## 배운 것

1. ### Navigation Component

   네비게이션 컴포넌트를 이곳 저곳 스터디에서 되게 많이 접했는데 (구글에서 그만큼 밀고 있는걸까...?) 스터디에서만 써보고 딱히 이후에 써볼 일이 없어서 크게 와닿지 않았던 개념이었다. 이번 과제에서 적용해보니까 이럴 때, 이렇게 쓰는구나를 확실히 배워가는 것 같다.

   

2. ### Util

   패키징을 항상 해야지 생각하면서도 패키징 사례를 볼 때마다 있는 이 Util 패키지가 도대체 뭐에 쓰는 걸까 항상 궁금했는데 이런 파일들을 넣어두는구나 알게 되었다. 아무래도 나는 따로 쓸 Util 클래스나 확장 함수가 없어서 그동안 볼 일이 없었던 듯 하다. 이번에 확실히 배웠으니 다음에 더 응용해서 써볼 수 있을 것 같다.



























