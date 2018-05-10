# WalmartHomeTake
1. Tech Stack:
    <br/>
    Android, Java<br/>
    Retrofit: Network requests, response <br/>
    JUnit, Mockito: Test Cases<br/>
    <br/>
2. Design Pattern:<br/>
   For sake of readability this app folder structure has model, view, presenter
   <br/>
   MVP<br/> 
   i)Model: It holds all model(pojo) classes, network layer, and some utils<br/>
   ii)View: It holds View components such as Activities , Fragments, Adapters.<br/>
          Activities are the Views here(means which implement View interfaces)<br/>
   iii)Presenter: It holds Presenter interfaces and its Implemetation classes
        Presenter will act as a middle man between View and Model<br/>

3. Features/Functionalities:<br/>
    1. Action Bar Search Navigation Bar, where user can enter his input and search for items. - <b>Please refer improvement section</b><br/>
    2. User can see list of (10) products as a CardView which includes Image Thubmnail, product title, price , and description<br/>
    3. Once user tap on any cardview he will be navigating to Details screen. <br/> - <b>Please refer Improvement section</b><br/>
        Details screen will show selected Product's title, big image, price, description and <b>Recommended Products</b> <br/>
    4. Details Screen will make a retrofit call to walmart recommended API and fetch products based on the user selected item in step-3<br/>
    5. <b>Most Importantly Orientation handled, and UI also compatible with Multiple devices such as handsets, tablets</b> - <b>Please refer Improvement section</b> <br/>
    6. Recommended Items at bottom of the screen are tappable and will open Details Screen
    
4. Communication Between Screens:<br/>
    1. HomeActivity and DetailsActivity both implemented FragmentCallback interface for communication between Fragment and Activity<br/>
    2. HomeActivity will send the Serializable <b>Item</b> Object in Intent and send it to DetailsScreen after user selected any item - <b>Please refer Improvement section</b><br/>
    3. Again between activities to Fragment data  will also passed in intent.<br/>
    
5. <b>Test Cases:</b><br/>
   I mainly focused on testing Presenter layer where  making sure API giving proper results or not.
   There are 2 JUnit calsses to test Retrofit network classes.- <b>Please refer Improvement section</b> <br/>
   
6. Improvements/Opportunities:<br/>
    1. I Create BaseActivity to serve as a parent class for HomeActivity & ItemDetailsActiivty which supposed to hold Search Bar Navigation, and other common stuff like showing progress on the screen. So this way Search Option will be available for user in each and every screen. For time being I didnt completed this. But I hope you can understand design appoach in this scenario.<br/>
    2. In the HomeScreen as well as Details Screen we can show more fields such as rating, availability but for now I limitted to some.<br/>
    3. HomeScreen list item layout can be modified for more good looking UI(mainly Image) in tablet by having different dimesnions<br/>
    4. Passing Seriliazable object between Activities is not a good practice , instead we can create Parcellable objects.<br/>
    5. We can write test cases for UI also but for now I only focused on Presenter layer.<br/>
7. Known issues:<br/>
   Once user sees the list of items and on device back press then search bar is not showing up again - As a fix I believe moving search bar nav to BaseActivity might solve this<br/>
  


