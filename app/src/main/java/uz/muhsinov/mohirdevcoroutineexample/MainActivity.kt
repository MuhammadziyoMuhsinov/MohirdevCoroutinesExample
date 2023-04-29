package uz.muhsinov.mohirdevcoroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val answer = async {doNetworkCall()}
            val answer2 = async { doNetworkCall2() }

            withContext(Dispatchers.Main){
                Toast.makeText(this@MainActivity, "${answer.await()} ${answer2.await()}", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private suspend fun doNetworkCall(): String {
        delay(2000)
        return "Answer from network call"
    }
    private suspend fun doNetworkCall2(): String {
        delay(3000)
        return "Answer from network call2"
    }
}