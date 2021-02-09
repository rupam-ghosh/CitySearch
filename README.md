Hello Folks at Backbase,
Thanks for taking the time to evaluate my coding assignment.
The code for the coding assignment is written in Java.

**How to run the app ?**
1. Open CitySearch folder using Android Studio.
2. Make sure gradle sync is complete without errors.
3. Make sure you select the "app" configuration in the Run drop down.
4. Make sure you have an emulator running in the computer.
5. Click on Play button beside the device selector.
6. App should start and you should see the loading state of the app, 
    followed by the list of city displayed in the list after 1 - 2 seconds.

**How to run the unit tests?**

1. Open the project in Android Studio.
2. Go to androidTest Folder.
3. To Run a particular test, go to the test and click on the play button beside the name of the class.
   It should run all the tests in the file.
4. Repeat this for other test files as well.
5. If you want to run all tests in one go, right click on java in androidTest folder and click on Run 'All tests'
6. CityListViewModelTest requires an android emulator or phone to run as it uses ActivityTestRule

**Explanation of the search technique:**
Since the question mentions search using prefix, i have used a trie based in memory database.
For sorting the data, i have used Comparable in java.

**Explanation of app architecture:**
The android app is built using MVVM pattern.
The view layer is the CityListFragment.java( Responsible for displaying the data )
The viewmodel layer is CityListViewModel.java ( Responsible for business logic )
The model layer is CityListModel.java ( Responsible for fetching the data from the store )

**Explanation of the unit tests:**

1. CityListModelTest - this file tests the model logic and communication
2. CityListViewModelTest - this file tests the view model logic and communication
3. SearchAlgoTest - this file tests the search algorithm
4. TrieImplTest - this file tests the trie implementation

**Notes for the evaluator:**
1. You might notice the first search to be slow as i have used AsyncTask for both adding data to the store
    and fetching the data from the data store.
    Since both AsyncTask are executed serially, first search query waits for the insertion task to get over.
2. GSON library has been used to parse the JSON data.
3. Android Jetpack View model and liveData has been used.
4. androidx test library has been used and mockito has been used for mocking.
5. While writing tests, i have asserted that the json file contains 209557 entries
