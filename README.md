# NativeCache for android

--------------------------------------------------------------------
Cache library for local storage and memory in android

Options

- push boolean, string, float, int, long, anyObject
- pull boolean, string, float, int, long, anyObject
- cache with timeToLife
- clear all cache
- remove specific key
- check has specific key
- check valid specific key

### Usage

add to root build.gradle

```groovy

maven { url "https://jitpack.io" }

```

add to module build.gradle

```groovy

implementation 'com.github.nativeblocks:NativeCache-Android:{latest-version}'

```