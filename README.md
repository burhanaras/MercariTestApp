# Mercari Test App

Developed by [Burhan ARAS] with all the love on planet.

https://developer.android.com/topic/libraries/architecture/images/final-architecture.png

This application has been as an assignment project for the Mercary company of Japan. It's functionalities are as follows:

  - Fetch data from Mercari web service
  - Group by Man, Women and All Products then list them on regarding pages
  - Keep UI responsive when device is rotated or on tablets

# Architecture

We have developed this application using MVVM (Model-View-ViewModel) architecture which is recently recommended by google. We haved used the Kotlin programming language using industry-proven tools and libraries.

  - Reference 0: [Guide To App Architecture](https://developer.android.com/jetpack/docs/guide)
  - Reference 1:  [Developing Android Apps With Kotlin](https://www.udacity.com/course/developing-android-apps-with-kotlin--ud9012)



The main players in the MVVM pattern are:
  - The View — that informs the ViewModel about the user’s actions
  - The ViewModel — exposes streams of data relevant to the View
  - The DataModel — abstracts the data source. The ViewModel works with the DataModel to get and save the data.


In this MVVM architecture Activities and Fragments depend only on a view model. The repository is the only class that depends on multiple other classes; in this project, the repository depends on a persistent data model and a remote backend data source.

Repository is the single source of truth for all the app data and has a clean API for UI to communicate with.

Repository fetches data from network then it saves into local database and also notifies UI View classes.

This design creates a consistent and pleasant user experience. Regardless of whether the user comes back to the app several minutes after they've last closed it or several days later, they instantly see a user's information that the app persists locally. If this data is stale, the app's repository module starts updating the data in the background.
