package pt.eandrade.eandrade.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pt.eandrade.eandrade.R
import pt.eandrade.eandrade.ui.theme.ColorGithub
import pt.eandrade.eandrade.ui.theme.ColorLinkedIn
import pt.eandrade.eandrade.utils.openBrowserUrl

@Composable
@ExperimentalMaterialApi
fun SocialsComponent() {
    Column {
        Text(
            text = "Socials",
            style = MaterialTheme.typography.h5
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ){
            CurriculumSocialsCard(
                url = stringResource(R.string.github_url),
                color = ColorGithub,
                drawableId = R.drawable.ic_github,
                network = stringResource(R.string.github_title),
                description = stringResource(R.string.github_username),
            )
            CurriculumSocialsCard(
                url = stringResource(R.string.linkedin_url),
                color = ColorLinkedIn,
                drawableId = R.drawable.ic_linkedin,
                network = stringResource(R.string.linkedin_title),
                description = stringResource(R.string.linkedin_username),
            )
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun CurriculumSocialsCard(url: String, color: Color, drawableId: Int,
                          network: String, description: String ) {
    val context = LocalContext.current
    Card(
        onClick = {
            openBrowserUrl(context, url)
        },
        backgroundColor = color
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(drawableId),
                contentDescription = network
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = description
            )
        }
    }
}