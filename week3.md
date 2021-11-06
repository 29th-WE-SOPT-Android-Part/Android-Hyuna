# Android Assignment week 3

## level 1 필수 과제


이번 과제는 '과제를 성공적으로 마치고 나서 이런 것을 느꼈다' 가 아니라 '과제를 하면서 이런 것에 부딪혔다' 가 주된 흐름이었다. 그래서 그 부분들에 대해 무엇이 문제였고, 어떻게 해결했는지를 추가하여 이번주 리드미를 작성해보려고 한다.

------

### 실행 화면

📌 로그인 화면

<img src="https://user-images.githubusercontent.com/63237214/140601825-a7356700-fa9d-4098-93c5-b01addae4b8c.png" alt="image" width="200" height="450" /><img src="https://user-images.githubusercontent.com/63237214/140601785-c88f7871-883e-4097-b130-f77a109012a6.png" alt="image" width="200" height="450" /><img src="https://user-images.githubusercontent.com/63237214/140601806-7828028d-79d0-4fe4-b78f-36db0ef1c8d2.png" alt="image" width="200" height="450" />

</br>

📌 회원가입 화면

<img src="https://user-images.githubusercontent.com/63237214/140601845-90fcb202-8db8-4e1f-84bb-9ec42f34a6e3.png" alt="image" width="200" height="450" /><img src="https://user-images.githubusercontent.com/63237214/140601870-34405276-f128-48fd-8036-fd2e4eed2b45.png" alt="image" width="200" height="450" /><img src="https://user-images.githubusercontent.com/63237214/140601881-32d11489-5f6b-4693-8720-4545ca03a8ed.png" alt="image" width="200" height="450" />

</br>

📌 ProfileFragment.kt

<img src="https://user-images.githubusercontent.com/63237214/140602797-b9ed103d-dfa8-4e8e-b4d2-7edbc55a61bb.png" alt="image" width="200" height="450" /><img src="https://user-images.githubusercontent.com/63237214/140602815-23d2f5f2-5eae-4e67-bced-c121463a770c.png" alt="image-20211106163615502" width="200" height="450" />

</br>

📌 HomeFragment.kt

<img src="https://user-images.githubusercontent.com/63237214/140601922-51ab227b-75f3-46a7-b0ac-451e1c1404ce.png" alt="image" width="200" height="450" /><img src="https://user-images.githubusercontent.com/63237214/140601931-81e6515d-1e9d-40a3-a129-4dfb6e646457.png" alt="image" width="200" height="450" />

</br>

------

### 구현 로직

📌 로그인, 회원가입 화면

```xml
//activity_signin.xml, activity_signup.xml 파일

<EditText
    ...
    android:background="@drawable/selector_input" //selector 활용
    ... />
```

```xml
//selector_input.xml 파일

<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:drawable="@drawable/edge_pink"
        android:state_focused="true" /> //focus 되었을 때 분홍 테두리
    <item
        android:drawable="@drawable/fill_gray"
        android:state_focused="false" /> //focus 안 되었을 때 회색 박스
</selector>
```

</br>

📌 ProfileFragment.kt

```kotlin
	private fun initTransaction(){
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        childFragmentManager.beginTransaction().add(R.id.container_recycle,followerFragment).commit()

        //btnFollower 버튼이 눌리면 followerFragment로 transaction
        binding.btnFollower.setOnClickListener { 
            binding.btnFollower.isSelected = true
            binding.btnRepository.isSelected = false
            var position = childFragmentManager.beginTransaction()
            position.replace(R.id.container_recycle, followerFragment)
            position.commit()
        }

        //btnRepository 버튼이 눌리면 repositoryFragment로 transaction
        binding.btnRepository.setOnClickListener {
            binding.btnFollower.isSelected = false
            binding.btnRepository.isSelected = true
            var position = childFragmentManager.beginTransaction()
            position.replace(R.id.container_recycle,repositoryFragment)
            position.commit()
        }
    }

	// Glide의 CircleCrop 기능 활용
	private fun initImage() {
        Glide.with(this)
            .load(R.drawable.image)
            .circleCrop()
            .into(binding.ivProfile)
    }
```

</br>

📌 HomeFragment.kt

```kotlin
//
private fun initAdapter(){
    val fragmentList = listOf(ViewPagerFollowingFragment(), ViewPagerFollowerFragment())
    
    tabViewPagerAdapter = TabViewPagerAdapter(this)
    tabViewPagerAdapter.fragments.addAll(fragmentList)
	
    binding.vpFollow.adapter = tabViewPagerAdapter //ViewPater2와 Adapter 연동
}


private fun initTabLayout(){
    val tabLable = listOf("팔로잉", "팔로워")

    //TabLayoutMediator로 ViewPager2와 TabLayout 연동
    TabLayoutMediator(binding.tlFollow, binding.vpFollow) {tab, position ->
        tab.text = tabLable[position]
    }.attach()
}
```

</br>

------

### 문제점과 해결

1) **ProfileFragment.kt 파일에서 버튼의 seletor속성이 적용되지 않음**

📌ProfileFragment.kt

```kotlin
binding.btnFollower.setOnClickListener {
    binding.btnFollower.isSelected = true //follower 선택시 true
    binding.btnRepository.isSelected = false
    var position = childFragmentManager.beginTransaction()
    position.replace(R.id.container_recycle, followerFragment)
    position.commit()
}

binding.btnRepository.setOnClickListener {
    binding.btnFollower.isSelected = false
    binding.btnRepository.isSelected = true //repository 선택시 true
    var position = childFragmentManager.beginTransaction()
    position.replace(R.id.container_recycle,repositoryFragment)
    position.commit()
}
```

문제의 버튼. 해결하기까지 정말 오래걸렸다.

구글링해서 얻은 첫번째 해결책) buttion view 태그를 androidx.appcompat.widget.AppCompatButton로
material 을 상속받으면 button의 background 속성이 적용되지 않는다고 한다. 그래서 theme 파일을 보니까 라이트모드에서는 parent가 material로 지정되어 있지도 않았다. 그래도 혹시나 하는 마음에 태그를 바꿔줬지만 역시 변하는 건 없었다. 생각해보니 애초에 색상 자체가 적용되지 않는 건 아니었다. selected 속성을 지워주면 귤색으로만, 회색으로만 나오게는 할 수 있었다. 그럼 selected가 문제인 것 같은데, 아무리 생각해도 그게 어디서 왜 문제가 된건지 모르겠어서 계속 구글링을 했다.

구글링해서 얻은 두번째 해결책) 버튼 클릭시 selected 여부를 설정
고민할수록 뭔가 디자인적인 문제인 것 같아서  layout이랑 drowable 파일만 주구장창 팠다. 그러다가 어떤 블로그에서 .kt 파일을 변경하는 걸 봤는데 여기서 아차 싶었다. menu나 edit text 에서 설정했던 checked나 focused와는 달리 selected는 해당 버튼이 선택 되었는지 여부를 따로 지정해줘야 했다. selector xml파일에 모두 같은 코드를 적었는데 왜 유독 이 버튼만 적용이 안되는지 답답했는데 사이다를 한 열 캔쯤 따서 마신 기분이었다.... ~~(동시에 왜 이 생각을 못했지 자괴감도...)~~ 분명 처음에는 ProfileFragment.kt에서 접근했던 것 같은데 왜 산으로 가게 되었는지 모르겠지만.. 어쨌든 결국 해결했다.... 

</br>

2) **ProfileFragment.kt 파일에서 Gilde를 이용한 이미지가 뜨지 않음**

📌ProfileFragment.kt

```kotlin
class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        initTransaction()
        initImage() // 함수 호출
        return binding.root
    }
```

initImage( )  함수를 작성만 하고 호출을 안했다. 이래놓고 왜 이미지가 안 뜨는지 한참 고민했다. 제정신이 아닌듯.

</br>

3) **HomeFragment.kt로 넘어갈 수 없음 (initAdapter() 함수의 문제로 추정)**

📌HomeFragment.kt

```kotlin
private fun initAdapter(){
        val fragmentList = listOf(ViewPagerFollowingFragment(), ViewPagerFollowerFragment())

        tabViewPagerAdapter = TabViewPagerAdapter(this) //문제의 this..
        tabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpFollow.adapter = tabViewPagerAdapter
    }
```

 this가 HomeFragment로 인식?되어서 자꾸 빨간 줄이 쳐진 것 같은데 alt enter를 막 눌렀더니 TabViewPagerAdapter가 뭔가 변경된 것 같고 (아닐지도) 그 이후로 정상 작동했다. 일단 해결은 했지만 여긴 뭐가 문제인지 더 공부해야 할 것 같다.

</br>

4) **문제점 3의 연장선에서,, 애뮬레이터로 어떤 상태인 지 확인할 수가 없어 TabLayout과 ViewPager2를 합친 뷰는 제대로 구현됐는지 알 수가 없음**

애뮬레이터 실행해보니 여긴 생각보다 잘 돌아가서 일단 만들어만 두었던 view pager xml 파일에 준비중이라는 메세지 띄우도록 조금 수정해주었다.

</br>

------

### 느낀 것들

1. **전체적인 구조를 볼 줄 알아야 한다.**</br>
   어떤 액티비티에 어떤 프래그먼트가 있고 어떤 뷰파저가 있는지 이런 구조가 명확하게 잡히지 않은 채로 얼레벌레 과제를 진행하니 어디서부터인가 꼬이기 시작하면서 결국은 뭐가 어디에 연결되는 지도 모르겠는 상황에 이르렀다. 그 상태에서는 당연히 오류를 해결할 수가 없었다. 머리를 싹 비우고 조금 더 구조를 파악한 후에 다시 들여다보니 이렇게 간단할 수가 없었다......</br></br>
2. **시간적 여유를 두고 해야한다.**</br>
   앞서 말한 구조를 보지 못한 이유가 시간적 여유가 없어서였던 것 같다. 마음이 급하니까 일단 마구잡이로 이것저것 적용하긴 하는데 시간은 없고 이해는 안되고.. 당연히 머리가 안 돌아갈 수 밖에 없는 것 같다. ~~(코드도 안 돌아가고)~~ </br></br>
3. **코틀린 공부가 필요하다.**</br>
   머릿속에 있는 걸 어떻게 표현해야 하는지 헤매다가 자꾸 세션 코드를 참고하게 되는데 그러다보니 실력이 하나도 늘지 않는 것 같다. 게다가 겨우겨우 쓴 코드도 깔끔하지가 못한 것 같다. 가독성이 떨어지니까 어디서 문제인지 찾기도 힘들다. 코틀린 공부가 필요한 것 같다. 하지만 언제...? ~~(6전공+2개발동아리의 늪)~~

