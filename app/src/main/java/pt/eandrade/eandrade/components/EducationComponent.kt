package pt.eandrade.eandrade.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EducationComponent() {
    Text(
        text = "Education",
        style = MaterialTheme.typography.h5
    )

    Column {
        for (item in listEducation) {
            ListItem(
                text = { Text(item.title) },
                secondaryText = { Text(item.course) },
                //icon { Image(painter = painterResource(id = R.drawable.p1), contentDescription = null)  },
                overlineText = { Text("${item.school} / ${item.duration}") },
                singleLineSecondaryText = false
            )
        }
    }
}

data class Education(val school: String, val title: String,
                 val course: String, val duration: String, val imageUrl: String)
val listEducation = listOf(
    Education("Polytechnic of Leiria - School of Technology and Management",
        "MSc, Informatics", "Master's degree in mobile computing", "2017-2020",
        ""),
    Education("Polytechnic of Leiria - School of Technology and Management",
        "BSc, Informatics", "Bachelor’s degree in computer engineering.", "2013-2017",
        ""),
)