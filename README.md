# Mercari MVVM App

Developed by [Burhan ARAS] with all the love on planet.

This application has been as an assignment project for the Mercary company of Japan. It's functionalities are as follows:

  - Fetch data from Mercari web service
  - Group by Man, Women and All Products then list them on regarding pages
  - Keep UI responsive when device is rotated or on tablets

# Architecture

We have developed this application using **MVVM (Model-View-ViewModel)** architecture which is recently recommended by google. We haved used the **Kotlin** programming language using industry-proven tools and libraries.

  - Reference 0: [Guide To App Architecture](https://developer.android.com/jetpack/docs/guide)
  - Reference 1:  [Developing Android Apps With Kotlin](https://www.udacity.com/course/developing-android-apps-with-kotlin--ud9012)



The main players in the MVVM pattern are:
  - **The View** — that informs the ViewModel about the user’s actions
  - **The ViewModel** — exposes streams of data relevant to the View
  - **The DataModel** — abstracts the data source. The ViewModel works with the DataModel to get and save the data.


In this MVVM architecture Activities and Fragments depend only on a view model. The repository is the only class that depends on multiple other classes; in this project, the repository depends on a persistent data model and a remote backend data source.

Repository is the single source of truth for all the app data and has a clean API for UI to communicate with.

Repository fetches data from network then it saves into local database and also notifies UI View classes.

Also we have implemented a background worker using **WorkManager** to run periodically in the background and keep local db up to date. We have configured worker to run once a day.

This design creates a consistent and pleasant user experience. Regardless of whether the user comes back to the app several minutes after they've last closed it or several days later, they instantly see a user's information that the app persists locally. If this data is stale, the app's repository module starts updating the data in the background.

Architecture of Mercari MVVM App: 
![alt text](https://github.com/burhanaras/MercariTestApp/blob/master/screenshots/mvvm.png "Mercari MVVM App architecture")


### Tech

We have used popular, industry-proven tools and libraries :

* **Architecture Components - ViewModel** We keep UI related logic here.
* **Architecture Components - LiveData** We keep data that UI needs. Fragments observe this LiveData
* **DataBinding** To bind XML UI with data
* **Room** To save data to local db
* **Android Material** To benefit new Android Material design library
* **Android KTX**  provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
* **Coroutines** To fetch data in background threads. (We no longer need RxJava)
* **WorkManager** To fetch data in the background periodically.
* **Retrofit** To connect a web service
* **Stetho** To trace the network requests and see local db content
* **Moshi** To parse Json
* **LeakCanary** To detect memory leaks
* **MonkeyRunner** To test UI crashes crazily :)
* **Lint** To see warnings in our code
* **JUnit - Espresso** To write automated tests.



**Burhan ARAS**

   [Burhan ARAS]: <http://www.burhanaras.net>


