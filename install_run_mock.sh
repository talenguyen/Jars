./gradlew :app:installMockDebug
adb shell am start  -n "vn.tale.ub.mock/vn.tale.jars.ui.list.ListUserActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER

