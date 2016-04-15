./gradlew :app:installDevDebug
adb shell am start  -n "vn.tale.ub.dev/vn.tale.jars.ui.list.ListUserActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER

