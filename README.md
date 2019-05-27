# TMDb Client

Open-source TMDb client for Android.

## Overview

This is a demonstration of applying `Clean Architecture` + `MVVM` + `Router` approach on application with the couple of scenarios:
* Fetch and display few discovery sections
* Display movie details

Discovery sections support pagination to provide experience of infinite scrolling. Also, Discovery content is scrollable in both vertical and horizontal directions and allows to add new sections if necessary.

Navigation mechanism supports phone and tablet navigation. In case of tablet mode it'll display two panels with discovery catalog and movie details simultaneously.

## Build

To build and install this application connect a device or emulator, then run the following command:

```shell
./gradlew installDebug
```

Or build an **.apk** file and then install it on device manually:

```shell
./gradlew assembleDebug
```
## Dependencies

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Paging library](https://developer.android.com/topic/libraries/architecture/paging)
* [Cicerone](https://github.com/terrakok/Cicerone)
* [Dagger 2](https://github.com/google/dagger)
* [AutoFactory](https://github.com/google/auto/tree/master/factory)
* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
* [Moshi](https://github.com/square/moshi)
* [OkHttp](https://github.com/square/okhttp)
* [Retrofit](https://github.com/square/retrofit)
* [Glide](https://github.com/bumptech/glide)
* [ThreeTen Android Backport](https://github.com/JakeWharton/ThreeTenABP)

## License


    Copyright 2019 Victor Kosenko

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

