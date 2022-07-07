# eandrade-cv

This repository contains a basic curriculum app fully built in Kotlin for the Android platform.

It was created to showcase my work and resumé, as well as a means to learn Android's newest UI toolkit, Jetpack Compose.

## Features

The app contains several categories with information about myself, such as:
- Basic information
- Skills
- Experience
- Education
- Contacts
- Relevant social networks

## Technologies used

Besides Compose, several other tools were used in the development of this app. Here's a list and brief explanation for the usage of each of them.

- [Firebase](https://firebase.google.com)
  - Used as a data storage to keep images and text. Most curriculum content in the app can be directly updated through Firebase without the need to edit a single line of code.
- [Retrofit](https://square.github.io/retrofit/)
  - HTTP client used to obtain text data from Firebase. Retrofit was picked because it is fast both in implementation and in its job
- [Landscapist](https://github.com/skydoves/landscapist)
  - An image-loading library that is able to fetch images over the network. Perfect to use with our Firebase data storage.
- [Lifecycle ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - Used in conjunction with Retrofit to manage data retrieved from Firebase throughout the application's lifecycle
- [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
  - App navigation structure built with Compose's own Navigation component
- [Gson](https://github.com/google/gson)
  - There were some serialization needs to convert data to and from Firebase, Gson gets the job done painlessly


## Test the app

You can find a compiled APK ready to install on the [releases](https://github.com/eandrade-dev/eandradecv/releases) section of this repository. Remember that this APK is not distributed via Google Play, so you will need to have enabled the option to install apps from unknown sources.

# Thank you
If you've come this far, thank you for reading and hopefully you get to test the app yourself. I had a blast learning Compose and how other components interacted with it, so if you're here for that, I hope you enjoy it too.
