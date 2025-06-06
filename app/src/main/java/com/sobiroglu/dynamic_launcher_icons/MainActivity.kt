package com.sobiroglu.dynamic_launcher_icons

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sobiroglu.dynamic_launcher_icons.model.appLauncherIcons
import com.sobiroglu.dynamic_launcher_icons.ui.theme.DynamicAppIconTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicAppIconTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        appLauncherIcons.forEach {
                            Button(
                                onClick = {
                                    changeIcon(
                                        it.launcherIconId,
                                        context
                                    )
                                }
                            ) {
                                Text(
                                    text = buildString {
                                        append("Apply ")
                                        append(it.launcherIconId.replaceFirstChar { it.uppercase() })
                                        append(" Icon")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun changeIcon(iconId: String, context: Context) {
    val icon = appLauncherIcons.find { it.launcherIconId == iconId } ?: return
    val packageManager = context.packageManager

    appLauncherIcons.filter { it.launcherIconId != iconId }.forEach {
        disableComponent(context, packageManager, it.component)
    }
    enableComponent(context, packageManager, icon.component)
}

private fun enableComponent(
    context: Context,
    packageManager: PackageManager,
    componentClassName: String
) {
    val component = ComponentName(context, componentClassName)
    packageManager.setComponentEnabledSetting(
        component,
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP
    )
}

private fun disableComponent(
    context: Context,
    packageManager: PackageManager,
    componentClassName: String
) {
    val component = ComponentName(context, componentClassName)
    packageManager.setComponentEnabledSetting(
        component,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP
    )
}