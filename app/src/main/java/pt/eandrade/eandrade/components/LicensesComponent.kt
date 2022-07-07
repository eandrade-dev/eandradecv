package pt.eandrade.eandrade.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import pt.eandrade.eandrade.MainActivity.Companion.NAV_LICENSES_ROUTE

@Composable
fun LicensesComponent(navController: NavHostController){
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxSize()
    )
    {
        Text(
            text = "Licenses",
            style = typography.h5
        )
        Text(
            text = "This project uses several open-source libraries",
            modifier = Modifier.padding(8.dp)
        )
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = {
                    navController.navigate(NAV_LICENSES_ROUTE)
                },
            ) {
                Text(text = "Tap here to know more")
            }
        }
    }
}

@Composable
fun LicensesDetailsComponent(navController: NavHostController){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Licenses",
            style = typography.h5,
            fontWeight = FontWeight.Bold
        )
        Column(Modifier.padding(8.dp)) {
            Text(
                text = "Landscapist by Jaewoong Eum (skydoves@github)",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "https://github.com/skydoves/landscapist"
            )
            Text(
                text = "Apache-2.0 license"
            )
        }
        Column(Modifier.padding(8.dp)) {
            Text(
                text = "Retrofit by Square",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = " https://square.github.io/retrofit/"
            )
            Text(
                text = "Apache-2.0 license"
            )
        }
        Column(Modifier.padding(8.dp)) {
            Text(
                text = "Gson by Google",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "https://github.com/google/gson"
            )
            Text(
                text = "Apache-2.0 license"
            )
        }
    }
}