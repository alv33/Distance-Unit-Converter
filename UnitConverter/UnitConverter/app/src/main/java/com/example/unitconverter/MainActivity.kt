package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitCOnverter()
                }
            }
        }
    }
}

@Composable
fun UnitCOnverter(){
    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpendent by remember { mutableStateOf(false) }
    var oExtendent by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.DarkGray
    )

    fun convertUnits(){
        // ?: is elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?:0.0
        val result = (inputValueDouble * conversionFactor.value*100.0 /oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //here all the UI elements will be stacked below other\
        Text("Unit Converter",
            style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp)) // Modifire will determine the distance
        OutlinedTextField(value = inputValue, onValueChange = {
            //here goes what should happen when the value of our OutlineTextField changes})
            inputValue = it
            convertUnits()
        },
        label = {Text("Enter Value")})
        Spacer(modifier = Modifier.height(16.dp)) // Spacer will make space between contexts
        Row {
            //here all the UI elements will be stacked below other
            //Input Box
            Box {
                //Input button
                Button(onClick = { iExpendent = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpendent, onDismissRequest = { iExpendent =false }) {
                    DropdownMenuItem(text = { Text("Centimeters")},
                        onClick = {
                            iExpendent = false
                            inputUnit ="Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()

                         })
                    DropdownMenuItem(text = { Text("Meters")},
                        onClick = {
                            iExpendent = false
                            inputUnit ="Meters"
                            conversionFactor.value = 1.0
                            convertUnits()

                        })
                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            iExpendent = false
                            inputUnit ="Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()

                        })
                    DropdownMenuItem(text = { Text("Milimeters")},
                        onClick = {
                            iExpendent = false
                            inputUnit ="Milimeters"
                            conversionFactor.value = 0.001
                            convertUnits()

                        })
                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                //Output button
                Button(onClick = {oExtendent = true }) {
                    Text(outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExtendent, onDismissRequest = { oExtendent = false }) {
                    DropdownMenuItem(text = { Text("Centimeters")},
                        onClick = {
                            oExtendent = false
                            outputUnit ="Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Meters")},
                        onClick = {
                            oExtendent = false
                            outputUnit ="Meters"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            oExtendent = false
                            outputUnit ="Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Milimeters")},
                        onClick = {
                            oExtendent = false
                            outputUnit ="Milimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        })
                }

            }


        }
        Spacer(modifier = Modifier.height(16.dp))
        //result text
        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
            )
    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitCOnverter()
}