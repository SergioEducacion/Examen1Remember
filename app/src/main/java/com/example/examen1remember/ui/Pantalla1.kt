package com.example.examen1remember.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.examen1remember.data.DataSource
import com.example.examen1remember.data.LoteriaTipo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLoteria(
    modifier: Modifier = Modifier,
    loterias: ArrayList<LoteriaTipo> = DataSource.loterias,
    onClickCambiarPantalla: () -> Unit
) {
    var textoMostrar: MutableState<String> =
        remember { mutableStateOf("No has jugado ninguna loteria") }
    var gastadoTotal: MutableState<Int> = remember { mutableStateOf(0) }
    var jugadoVeces: MutableState<Int> = remember { mutableStateOf(0) }
    var ganadoTotal: MutableState<Int> = remember { mutableStateOf(0) }


    var nombreLoteriaTextEditor: MutableState<String> = remember { mutableStateOf("") }
    var dineroApostadoTextEditor: MutableState<String> = remember { mutableStateOf("0") }
    val dineroApostadoLoteria = dineroApostadoTextEditor.value.toInt();

    Column() {
        Text(
            text = "Bienvenido a apuestas XXX",
            modifier = modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                //.weight(0.25f)
                .padding(start = 20.dp, top = 50.dp)
        )
        LoteriasScroll(
            modifier,
            loterias,
            gastadoTotal,
            dineroApostadoLoteria,
            jugadoVeces,
            ganadoTotal,
            textoMostrar
        )

        TextFieldyBoton(
            nombreLoteriaTextEditor,
            dineroApostadoTextEditor,
            loterias,
            gastadoTotal,
            dineroApostadoLoteria,
            jugadoVeces,
            ganadoTotal,
            textoMostrar,
        )
        TextoActualizandose(modifier, jugadoVeces, gastadoTotal, ganadoTotal, textoMostrar)

        Button(
            onClick = onClickCambiarPantalla,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Cambiar de pantalla")
        }
    }
}

@Composable
private fun TextoActualizandose(
    modifier: Modifier,
    jugadoVeces: MutableState<Int>,
    gastadoTotal: MutableState<Int>,
    ganadoTotal: MutableState<Int>,
    textoMostrar: MutableState<String>
) {
    Column(
        modifier = modifier
            .fillMaxWidth().height(200.dp)
            .background(Color.LightGray)
            //.weight(0.25f)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = textoMostrar.value,
        )
        Text(
            text = "Has jugado " + jugadoVeces.value + " veces en loteria",
        )
        Text(
            text = "Has gastado " + gastadoTotal.value + " euros en loteria",
        )
        Text(
            text = "Has ganado " + ganadoTotal.value + " euros en loteria",
        )
    }
}

@Composable
private fun LoteriasScroll(
    modifier: Modifier,
    loterias: ArrayList<LoteriaTipo>,
    gastadoTotal: MutableState<Int>,
    dineroApostadoLoteria: Int,
    jugadoVeces: MutableState<Int>,
    ganadoTotal: MutableState<Int>,
    textoMostrar: MutableState<String>,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
    ) {

        items(loterias) { loteria ->
            Card(
                modifier = modifier
                    .padding(8.dp)
                    .width(250.dp)
            ) {
                Text(
                    text = "Nombre: ${loteria.nombre}",
                    modifier = Modifier
                        .background(Color.Yellow)
                        .fillMaxWidth()
                        .padding(20.dp)
                )
                Text(
                    text = "Premio: ${loteria.premio.toString()}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Cyan)
                        .padding(20.dp)
                )
                Button(
                    onClick =
                    {

                        actualizarValoresDinero(
                            gastadoTotal,
                            dineroApostadoLoteria,
                            jugadoVeces,
                            ganadoTotal,
                            loteria,
                            textoMostrar,
                        )


                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Apostar")
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TextFieldyBoton(
    nombreLoteriaTextEditor: MutableState<String>,
    dineroApostadoTextEditor: MutableState<String>,
    loterias: ArrayList<LoteriaTipo>,
    gastadoTotal: MutableState<Int>,
    dineroApostadoLoteria: Int,
    jugadoVeces: MutableState<Int>,
    ganadoTotal: MutableState<Int>,
    textoMostrar: MutableState<String>,
) {

    Row() {
        TextField(
            value = nombreLoteriaTextEditor.value,
            singleLine = true,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            onValueChange = { nombreLoteriaTextEditor.value = it },
            label = { Text("Loteria") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        TextField(
            value = dineroApostadoTextEditor.value,
            singleLine = true,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            onValueChange = { dineroApostadoTextEditor.value = it },
            label = { Text("Dinero apostado") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
    }
    Button(
        onClick =
        {
            for (loteria in loterias) {
                if (loteria.nombre == nombreLoteriaTextEditor.value) {
                    actualizarValoresDinero(
                        gastadoTotal,
                        dineroApostadoLoteria,
                        jugadoVeces,
                        ganadoTotal,
                        loteria,
                        textoMostrar
                    )
                    //En cuanto encuentra una lotería con el mismo nombre y hace la acción termina la lambda por el return
                    return@Button;
                }
            }
            //Solo llega aquí si no ha encontrado ninguna loteria con el mismo nombre
            textoMostrar.value = "No existe ninguna loteria con ese nombre"
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Jugar loteria escrita")
        //Text(text = ejemplo.value)
    }
}

/*
Esta función se encarga de actualizar todos los valores, por eso se pasa MutableState
Solamente hay la excepción de "No existe ninguna loteria con ese nombre", la cual se hace directamente
en una lambda, pero la cual podría ser una llamada a una función
 */
private fun actualizarValoresDinero(
    gastadoTotal: MutableState<Int>,
    dineroApostadoLoteria: Int,
    jugadoVeces: MutableState<Int>,
    ganadoTotal: MutableState<Int>,
    loteria: LoteriaTipo,
    textoMostrar: MutableState<String>,
) {
    if (dineroApostadoLoteria == 0) {
        textoMostrar.value = "No se puede comprar una loteria con 0 euros";
        return;
    }
    val numeroLoteriaSacado = (0..4).random()
    val numeroLoteriaComprado = (0..4).random()

    gastadoTotal.value += dineroApostadoLoteria
    jugadoVeces.value += 1;

    if (numeroLoteriaSacado == numeroLoteriaComprado) {
        textoMostrar.value = "Has ganado la lotería";
        ganadoTotal.value += loteria.premio * dineroApostadoLoteria;
    } else {
        textoMostrar.value = "Has perdido a la lotería";
    }
}