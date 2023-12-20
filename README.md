![JUST Digital Diary](docs/images/nia-splash.jpg "JUST Digital Diary")

<a href="paste link here"><img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" height="70"></a>


<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Jessore_University_of_Science_%26_Technology_logo.jpg/180px-Jessore_University_of_Science_%26_Technology_logo.jpg" alt="Logo" width="90">
</p>
<p align="center">
  JUST Digital Diary - Multiplatform App For
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android">
  <img src="https://img.shields.io/badge/iOS-000000?style=for-the-badge&logo=ios&logoColor=white" alt="iOS">
  <img src="https://img.shields.io/badge/macOS-000000?style=for-the-badge&logo=apple&logoColor=white" alt="macOS">
  <img src="https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white" alt="Windows">
  <img src="https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black" alt="Linux">
  <img src="https://img.shields.io/badge/Web-4285F4?style=for-the-badge&logo=google-chrome&logoColor=white" alt="Web">
</p>


<p>This Project is owned by the Department of Computer Science and Engineering(<span class="department"><a href="your_department_link">CSE</a></span>) of <span class="university"><a href="your_university_link">Jashore University of Science and Engineering(JUST)</a></span></p>.


**Learn how this app was designed and built in the [design case study](https://goo.gle/nia-figma), [architecture learning journey](docs/ArchitectureLearningJourney.md) and [modularization learning journey](docs/ModularizationLearningJourney.md).**

This is the repository for the [  JUST Digital Diary ](https://github.com/khalekuzzamancse/JUST-Digital-Diary)
app. It is a **work in progress** 🚧.

**JUST Digital Diary** is a fully functional Android app built entirely with Kotlin and  Compose Multiplatform,Ktor. It
follows best design and development best practices such as design pattern and solid principle,clean code.
The app is currently in development. The `App Link`  [will be avaiable soon](https://play.google.com/store/apps/).

# Features

1. **Authentication:**
   - Secure user authentication to protect sensitive information.

2. **Search Employees of the University:**
   - Easily search for university employees with detailed information.

3. **Faculty Details:**
   - Access comprehensive details about each faculty member.

4. **Department Information:**
   - Explore information about each department, including faculty details, ongoing projects, and more.

5. **Department Employees:**
   - View information about employees within each department, facilitating efficient communication and collaboration.

6. **Other Useful Information:**
   - Access a wealth of additional information relevant to the university, such as events, news, and announcements.

7. **Sharing Notes:**
   - Seamlessly share and collaborate on notes among users for improved communication and teamwork.

## Screenshots

![Will be avaialbe soon](docs/images/screenshots.png "Screenshot showing For You screen, Interests screen and Topic detail screen")

# Development Environment

**JUST Digital Diary** uses the Gradle build system and can be imported directly into Android Studio (make sure you are using the latest stable version available [here](https://developer.android.com/studio)). 

# Architecture

The **JUST Digital Diary** app follows the
[official architecture guidance](https://developer.android.com/topic/architecture) 
and is described in detail in the
[architecture learning journey](docs/ArchitectureLearningJourney.md).

# Modularization

The **JUST Digital Diary** app has been fully modularized and you can find the detailed guidance and
description of the modularization strategy used in
[modularization learning journey](docs/ModularizationLearningJourney.md).

# Build

The app contains the usual `debug` and `release` build variants. 

In addition, the `benchmark` variant of `app` is used to test startup performance and generate a
baseline profile (see below for more information).

`app-nia-catalog` is a standalone app that displays the list of components that are stylized for
**JUST Digital Diary**.

The app also uses
[product flavors](https://developer.android.com/studio/build/build-variants#product-flavors) to
control where content for the app should be loaded from.

The `demo` flavor uses static local data to allow immediate building and exploring of the UI.

The `prod` flavor makes real network calls to a backend server, providing up-to-date content. At 
this time, there is not a public backend available.

For normal development use the `demoDebug` variant. For UI performance testing use the
`demoRelease` variant. 




# UI
The app was designed using [Material 3 guidelines](https://m3.material.io/). Learn more about the design process and 
obtain the design files in the [Now in Android Material 3 Case Study](https://goo.gle/nia-figma) (design assets [also available as a PDF](docs/Now-In-Android-Design-File.pdf)).

The Screens and UI elements are built entirely using [Jetpack Compose](https://developer.android.com/jetpack/compose). 

The app has two themes: 

- Dynamic color - uses colors based on the [user's current color theme](https://material.io/blog/announcing-material-you) (if supported)
- Default theme - uses predefined colors when dynamic color is not supported

Each theme also supports dark mode. 

The app uses adaptive layouts to
[support different screen sizes](https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes).

Find out more about the [UI architecture here](docs/ArchitectureLearningJourney.md#ui-layer).

# Performance

# License

**JUST Digital Diary** is distributed under the terms of the Apache License (Version 2.0). See the
[license](LICENSE) for more information.
