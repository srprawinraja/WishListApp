package com.example.unitconventor

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconventor.ui.theme.UnitConventorTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var tts:TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConventorTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ){
                UnitConvertor()
            }

        }
    }
}


    @Composable
    fun UnitConvertor() {
        var expandInp by remember { mutableStateOf(false) }
        var expandOtp by remember { mutableStateOf(false) }
        var inputValue by remember { mutableStateOf("") }
        var outputValue by remember { mutableStateOf("0.0") }
        var inputUnit by remember { mutableStateOf("Meter") }
        var outputUnit by remember { mutableStateOf("Meter") }
        val inputConversionFactor = remember { mutableDoubleStateOf(1.0) }
        val outputConversionFactor = remember { mutableDoubleStateOf(1.0) }

        fun unitConversion(){

            val inputValueDouble:Double=inputValue.toDoubleOrNull()?:0.0
            val result=(inputValueDouble*inputConversionFactor.doubleValue)*outputConversionFactor.doubleValue
            outputValue=result.toString()
            tts = TextToSpeech(this) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    val result = tts.setLanguage(Locale.getDefault())
                    if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                        tts.speak("the value in $outputUnit is $outputValue", TextToSpeech.QUEUE_FLUSH, null)
                    }
                }
            }
        }


        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){

            Text(text = "Result : $outputValue", color = Color.Green , fontSize = 30.sp )
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Unit Convertor", color = Color.Red , fontSize = 20.sp , fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue=it
            },
            label = { Text(text = "Enter The Value")}
        )
            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Box(){
                    Button(onClick = {
                        expandInp=true
                    }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown , contentDescription = "")
                    }
                    DropdownMenu(expanded = expandInp, onDismissRequest = { expandInp=false }) {
                        DropdownMenuItem(
                            text = { Text(text = "Centimeter") }, onClick = {
                                inputUnit="Centimeter"
                                inputConversionFactor.doubleValue=0.01
                            }
                        )
                        DropdownMenuItem(
                            text = {  Text(text = "Meter") }, onClick = {
                                inputUnit="Meter"
                                inputConversionFactor.doubleValue=1.0
                            }
                        )
                        DropdownMenuItem(
                            text = {  Text(text = "Feet") }, onClick = {
                                inputUnit="Feet"
                                inputConversionFactor.doubleValue=0.3048
                            }
                        )
                        DropdownMenuItem(
                            text = {  Text(text = "Millimeter") }, onClick = {
                                inputUnit="Millimeter"
                                inputConversionFactor.doubleValue=0.001
                            }
                        )

                    }
                }
                Spacer(modifier = Modifier.width(16.dp))

                Box(){
                    Button(onClick = {
                        expandOtp=true
                    }) {
                        Text(text = outputUnit)
                        Icon(Icons.Default.ArrowDropDown , contentDescription = "")
                    }
                    DropdownMenu(expanded = expandOtp, onDismissRequest = { expandOtp=false }) {
                        DropdownMenuItem(
                            text = { Text(text = "Centimer") }, onClick = {
                                outputUnit="Centimeter"
                                outputConversionFactor.doubleValue=100.0
                            }
                        )
                        DropdownMenuItem(
                            text = {  Text(text = "Meter") }, onClick = {
                                outputUnit="Meter"
                                outputConversionFactor.doubleValue=1.0
                            }
                        )
                        DropdownMenuItem(
                            text = {  Text(text = "Feet") }, onClick = {
                                outputUnit="Feet"
                                outputConversionFactor.doubleValue=3.28084
                            }
                        )
                        DropdownMenuItem(
                            text = {  Text(text = "Millimeter") }, onClick = {
                                outputUnit="Millimeter"
                                outputConversionFactor.doubleValue=1000.0
                            }
                        )

                    }

                }            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                unitConversion()
            }) {
                Text(text = "Convert")
            }
    }
}

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }

    @Preview(showBackground = true)
    @Composable
    fun UnitConvertorPreview() {
        UnitConventorTheme {
            UnitConvertor()
        }
    }
}