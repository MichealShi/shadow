-keep public class * implements com.tencent.tinker.loader.app.ApplicationLifeCycle {
    *;
}

-keep public class * extends com.tencent.tinker.loader.TinkerLoader {
    *;
}

-keep public class * extends com.tencent.tinker.loader.app.TinkerApplication {
    *;
}

#your dex.loader patterns here
-keep class com.tencent.tinker.loader.** {
    *;
}

-keep class com.independent.shadow.SampleApplication {
    *;
}

-keep class com.independent.shadow.BaseBuildInfo {
    *;
}
#retrofit2.x
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
