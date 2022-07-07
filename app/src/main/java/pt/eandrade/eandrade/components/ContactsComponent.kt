package pt.eandrade.eandrade.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pt.eandrade.eandrade.R

@Composable
@ExperimentalMaterialApi
fun ContactsComponent(onboarding: Boolean){
    var verticalArrangement = Arrangement.Center
    var horizontalAlignment = Alignment.CenterHorizontally

    if(!onboarding){
        Text(
            text = "Contacts",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.h5
        )
        verticalArrangement = Arrangement.Center
        horizontalAlignment = Alignment.Start
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(bottom = 16.dp)
            .fillMaxSize(),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        ContactRowVector(
            Icons.Filled.Phone,
            "Phone Number",
            stringResource(R.string.phone_number)
        )
        ContactRowVector( Icons.Filled.Email,
            "Email",
            stringResource(R.string.email)
        )
        ContactRowDrawable(
            R.drawable.ic_linkedin,
            "LinkedIn",
            stringResource(R.string.linkedin_username)
        )
        ContactRowDrawable(
            R.drawable.ic_github,
            "Github",
            stringResource(R.string.github_username)
        )
    }
}

@Composable
private fun ContactRowVector(imageVector: ImageVector,
                             contentDescription: String, textStr: String){
    Row {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(textStr)
    }
}

@Composable
private fun ContactRowDrawable(drawableId: Int,
                               contentDescription: String, textStr: String){
    Row {
        Icon(
            painter = painterResource(drawableId),
            contentDescription = contentDescription,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(textStr)
    }
}