# Add main specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Library/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the ProGuard
# include property in main.properties.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any main specific keep options here:

# If your main uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontwarn com.squareup.okhttp.**
