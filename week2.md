# Android Assignment week 2

## level 1 필수 과제

------



1. ### 실행화면

   📌org.sopt.androidassignment1.Follower Recycler View

   ![image](https://user-images.githubusercontent.com/63237214/138472122-aaa84057-aeea-46c2-bc28-07dbe7c59488.png)![image](https://user-images.githubusercontent.com/63237214/138472315-de19f068-9a72-4ee9-a365-09b63caf2301.png)

   

   

   📌 Repository Recycler View

   ![image](https://user-images.githubusercontent.com/63237214/138473398-b5f62e02-ae6b-4e92-8b36-6d5ca74f9b3b.png)![image](https://user-images.githubusercontent.com/63237214/138473206-68595a81-c633-453b-ab0b-fcfb69ae936f.png)

   

   ------

   

2. ### 구현한 로직

   📌 HomeActivity.kt

   ```kotlin
   private fun initTransactionEvent(){
       //각 프레그먼트 객체 생성
           val followerFragment = FollowerFragment()
           val repositoryFragment = RepositoryFragment()
          
    
    //처음 보여지는 프레그먼트는 follower list가 있는 프레그먼트
    supportFragmentManager.beginTransaction().add(R.id.container_recycle,followerFragment).commit()
   
       //org.sopt.androidassignment1.Follower 버튼 클릭시 follower list가 있는 프레그먼트로
           binding.btnFollower.setOnClickListener {
               var position = supportFragmentManager.beginTransaction()
               position.replace(R.id.container_recycle, followerFragment)
               position.commit()
           }
   
       //Repository 버튼 클릭시 Repository list가 있는 프레그먼트로
           binding.btnRepository.setOnClickListener {
               var position = supportFragmentManager.beginTransaction()
               position.replace(R.id.container_recycle,repositoryFragment)
               position.commit()
           }
   
       }
   ```

   

   📌 FollowerFragment.kt

   ```kotlin
   private fun initAdapter(){
           followerAdapter = FollowerAdapter()
           binding.followerRecyclerView.adapter = followerAdapter
           followerAdapter.followerList.addAll(
               // FollwerData 파일의 name, introduction 자리에 각각 아래 내용으로
               listOf(
                   FollowerData("문다빈", "안드로이드 파트장"),
                   FollowerData("장혜령", "iOS 파트장"),
                   FollowerData("김우영", "서버 파트장"),
                   FollowerData("이성현", "디자인 파트장"),
                   FollowerData("김송현", "운영팀장"),
               )
           )
           followerAdapter.notifyDataSetChanged()
       }
   ```

   

   📌 FollowerAdapter.kt (시간에 쫓겨서 여기서부터는 주석을 못 달았는데 시간 나는대로 ~~(시험 끝나는대로)~~ 채워두겠습니다,,,)

   ```kotlin
   class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(){
       val followerList = mutableListOf<FollowerData>()
   
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
           val binding = ItemFollowerlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
           return FollowerViewHolder(binding)
       }
   
       override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
           holder.onBind(followerList[position])
       }
   
       override fun getItemCount(): Int = followerList.size
   
       class FollowerViewHolder(private val binding: ItemFollowerlistBinding) : RecyclerView.ViewHolder(binding.root){
           fun onBind(data:FollowerData) {
               binding.name.text = data.name
               binding.introduction.text = data.introduction
           }
       }
   ```

   

   📌 FollowerData.kt

   ```kotlin
   data class FollowerData(
       val name : String,
       val introduction : String
   )
   ```

   

   📌 RepositoryFragment.kt , RepositoryAdapter.kt , RepositoryData.kt 도 위와 유사

   ------

   

3. ### 배운 내용

   > 🔥 ''혹시나''가 ''역시나''다. 🔥

   📌 Data 파일 연결

   처음에는 org.sopt.androidassignment1.Follower Data, RepositoryData 파일을 따로 만들지 않고 ~~귀찮아서~~ 그냥 UserData파일 하나로 갖다 썼는데 각 Fragment 파일의 listof 부분에서 계속 오류가 났다. 처음에는 원인조차 모르다가 data파일이랑 연결되어 있으니까 혹시 모르니 한번 각각 파일로 나눠줘볼까 했는데 역시나 그 부분을 고치니까 정상적으로 작동했다 ^____^;

   다음부터는 시간낭비하지 말고 혹시나 싶은 건 다 건드려보자,,

   ```kotlin
   data class UserData(
       val name : String,
       val introduction : String
   )
   // 처음에는 그냥 이렇게 해두고 두 파일 다 name이랑 instroduction이라고 (혼자 생각하면서) 각각 따로 작성했다.
   ```
   





















