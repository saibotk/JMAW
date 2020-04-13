# Java Mojang Api Wrapper (JMAW)
[![](https://jitpack.io/v/saibotk/JMAW.svg)](https://jitpack.io/#saibotk/JMAW)

Simple, easy-to-use API Wrapper for the [Mojang API](https://wiki.vg/Mojang_API), written in Java.

## Goals
- Simple
- Type-safe responses for each endpoint / element
- Custom exception for errors
- State-less

## API Coverage
Implemented:
- All endpoints, as found on https://wiki.vg/Mojang_API

Not included:
- Authentication API (https://wiki.vg/Authentication)

## Installation / Download
### Jitpack
#### Gradle
Add the JitPack repository:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency and replace **VERSION** with the **commit tag** / **release string** or **'-SNAPSHOT'** for the latest commit:
```
dependencies {
	implementation 'com.github.saibotk:JMAW:VERSION'
}
```

#### Other
[Configuration for maven, sbt, etc](https://jitpack.io/#saibotk/JMAW)

## Documentation
Can be found in **/docs** or available through Github pages here: https://saibotk.github.io/JMAW/

## Dependecies
- [Retrofit](https://github.com/square/retrofit) for HTTP requests ( Apache - 2.0 )
- [Gson](https://github.com/google/gson) for json deserialization ( Apache - 2.0 )

## Projects using JMAW
- [BlockMap](https://github.com/Minecraft-Technik-Wiki/BlockMap)

I'd be happy if you would let me know when you are using the library in one of your projects.

## Contribute
**Everybody is welcome to open an issue and / or create a PR to suggest changes.**

## License
```
MIT License

Copyright (c) 2019 saibotk

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

### Notice
Also respect the licenses found in individual files.
- [RuntimeDefaultTypeAdapterFactory](https://github.com/saibotk/JMAW/blob/master/src/main/java/de/saibotk/jmaw/RuntimeDefaultTypeAdapterFactory.java) originally from [gson/extras](https://github.com/google/gson/blob/b046ea28eeb819ecc30c3a39cb6912dc84fae015/extras/src/main/java/com/google/gson/typeadapters/RuntimeTypeAdapterFactory.java) modified by saibotk - Apache - 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
