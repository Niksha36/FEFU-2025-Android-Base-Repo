package co.feip.fefu2025

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
class MainActivity : ComponentActivity() {
    private lateinit var networkReceiver: NetworkReceiver
    private var savedText = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        networkReceiver = NetworkReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, filter)
        setContent {
            FEFU2025AndroidBaseRepoTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,

                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(buildAnnotatedString {
                            append("RETRIEVED DATA: ")
                            withStyle(style = SpanStyle(color = Color.Green, fontWeight = FontWeight.Bold, fontSize = 30.sp)) {
                                append("${intent.data?.getQueryParameter("param1")}")
                            }
                        })
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mysuperapp://test"))
                            startActivity(intent)
                        }) {
                            Text("SecondActivity")
                        }


                        /** Второе задание*/
                        Spacer(modifier = Modifier.height(40.dp))

/** технически задание выполнено, так как rememberSaveable делает то же самое, что и onSaveInstanceState :)*/
                        val text = rememberSaveable { mutableStateOf("0") }
                        onSaveInstanceState(Bundle().apply {
                            putString("text", text.value)
                        })
                        Text(text = text.value)
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {
                            text.value = (text.value.toInt() + 1).toString()
                        }) {
                            Text("+1")
                        }

                        /** второй способ через onSaveInstanceState*/
//                        Spacer(modifier = Modifier.height(40.dp))
//                        val text = rememberSaveable { mutableStateOf(savedText.toString()) }
//                        Text(text = text.value)
//                        Spacer(modifier = Modifier.height(20.dp))
//                        Button(onClick = {
//                            text.value = (text.value.toInt() + 1).toString()
//                            savedText += 1
//                        }) {
//                            Text("+1")
//                        }
                    }

                }


            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("text", savedText)
    }
}
