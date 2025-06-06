package com.sobiroglu.dynamic_launcher_icons.model

data class LauncherIcon(
    val launcherIconId: String,
    val component: String
)

val appLauncherIcons: List<LauncherIcon> = listOf(
    LauncherIcon(
        launcherIconId = "default",
        component = "com.sobiroglu.dynamic_launcher_icons.MainActivityDefault"
    ),
    LauncherIcon(
        launcherIconId = "winter",
        component = "com.sobiroglu.dynamic_launcher_icons.MainActivityWinter"
    ),
    LauncherIcon(
        launcherIconId = "spring",
        component = "com.sobiroglu.dynamic_launcher_icons.MainActivitySpring"
    ),
    LauncherIcon(
        launcherIconId = "summer",
        component = "com.sobiroglu.dynamic_launcher_icons.MainActivitySummer"
    ),
    LauncherIcon(
        launcherIconId = "autumn",
        component = "com.sobiroglu.dynamic_launcher_icons.MainActivityAutumn"
    ),
)