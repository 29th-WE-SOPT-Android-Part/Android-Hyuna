# Android Assignment week 1

## level 1 필수 과제

------



1. ### 실행화면

   📌SignInActivity

   ![image](https://user-images.githubusercontent.com/63237214/138415467-25aee1b9-8fce-493e-96d0-fec94f8ee57e.png)

   

   📌SignUpActivity

   ![image](https://user-images.githubusercontent.com/63237214/138415601-a24f4fad-99f2-4b53-be1e-f472a1f23278.png)

   

   📌HomeActivity

   ![image](https://user-images.githubusercontent.com/63237214/138415679-e6306b30-d250-4c16-b690-de9ec6feceac.png)

   ------

   

2. ### 구현한 로직

   📌 activity_signin.xml

   ```kotlin
   //hint 속성으로 미리보기
   android:hint="아이디를 입력해주세요"
   android:hint="비밀번호를 입력해주세요"
   
   //inputType="textPassword"로 입력 내용 가리기
   android:inputType="textPassword"
   ```

   

   📌 SignInActivity.kt

   - 로그인

     ```kotlin
     //id_input 과 pw_input에 입력된 값을 각각 변수로 저장
     var id = binding.idInput.text
     var pw = binding.pwInput.text
     
     binding.btnLogin.setOnClickListener { //btn_login 클릭시
         if (id.isNotEmpty() && pw.isNotEmpty()) { //각 변수가 모두 비어있지 않은 경우 intent로 화면전환
             val intent = Intent(this, HomeActivity::class.java) 
             startActivity(intent)
         } else if (id.isEmpty() || pw.isEmpty()) { //둘 중 하나라도 비어있으면
             Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show() //토스트 메세지 출력
         }
     
     }
     ```

   - 회원가입

     ```kotlin
     binding.btnSignin.setOnClickListener { //btn_signin 클릭시
         val intent2 = Intent(this, SignUpActivity::class.java) // intent로 화면 전환
         startActivity(intent2)
     }
     ```

   

   📌 activity_signup.xml

   ```kotlin
   //hint 속성으로 미리보기
   android:hint="이름을 입력해주세요"
   android:hint="아이디를 입력해주세요"
   android:hint="비밀번호를 입력해주세요"
   
   //inputType="textPassword"로 입력 내용 가리기
   android:inputType="textPassword"
   ```

   

   📌 SignUpActivity

   - 회원가입 완료

     ```kotlin
     //name_input_signup, id_input_signup, pw_input_signup에 입력된 값을 각각 변수로 저장
     var id = binding.idInputSignup.text
     var pw = binding.pwInputSignup.text
     var name = binding.nameInputSignup.text
     
     binding.btnFinish.setOnClickListener { //btn_finish 클릭시
         if (id.isNotEmpty() && pw.isNotEmpty() && name.isNotEmpty()) { //각 변수가 모두 비어있지 않은 경우 finish로 다시 이동
             finish()
         } else if (id.isEmpty() || pw.isEmpty() || name.isEmpty()) { //셋 중 하나라도 비어있으면
             Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show() //토스트 메세지 출력
         }
     }
     ```

   

   📌 home.xml

   ```kotlin
   <ImageView
           android:id="@+id/imageView"
           android:layout_width="164dp"
           android:layout_height="168dp"
           android:layout_marginTop="50dp"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintStart_toStartOf="parent"
           app:layout_constraintTop_toBottomOf="@+id/logo"
           app:srcCompat="@drawable/image" /> //res/drawable에 img파일 추가하여 사진 삽입
   ```

   ------

   

3. ### 배운 내용

   > 🔥 다시는 AndroidManifest.xml 파일을 무시하지 말자. 🔥

   📌 파일명 변경

   코드를 다 짰는데 아무리 애뮬레이터를 돌려도 앱 실행이 안 돼서 구글링을 몇 시간동안 했다. ~~앱 실행 하나때문에..~~

   구글링이고 뭐고 그냥 파일 하나하나 다 열어서 뭐가 잘못됐나 보자는 마음으로 AndroidManifest.xml을 열게 되었는데

   ```kotlin
   <activity
       android:name=".SignInActivity" //여기 이름이 MainActivity였다
       android:exported="true">
       <intent-filter>
           <action android:name="android.intent.action.MAIN" />
   
           <category android:name="android.intent.category.LAUNCHER" />
       </intent-filter>
   </activity>
   ```

   환장. 파일명 바꿀 때 다시는 잊지 않을 듯. ^^

   

   📌 Activity 추가

   앱 실행이 됐는데 화면 전환이 되어야 할 순간에 갑자기 앱이 종료됐다. 이번에도..? 하는 마음으로 다시 AndroidManifest.xml을 열게 되었는데

   ```kotlin
   <activity android:name=".SignUpActivity"/>
   <activity android:name=".HomeActivity"/>
   //이 두 개 Activity 추가를 안했다.
   
   ```

   환장 22. 파일 추가할 때 다시는 잊지 않을 듯. ^^






















