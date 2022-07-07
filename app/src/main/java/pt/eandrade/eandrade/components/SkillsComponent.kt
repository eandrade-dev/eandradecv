package pt.eandrade.eandrade.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import pt.eandrade.eandrade.FIREBASE_BASE_URL

@Composable
fun SkillsComponent() {
    Text(
        text = "Skills",
        modifier = Modifier.padding(bottom = 8.dp),
        style = MaterialTheme.typography.h5
    )

    val list = remember { skillsList }
    LazyRow(modifier = Modifier.padding(end = 16.dp)) {
        items(
            items = list,
            itemContent = { item ->
                SkillCard(item)
            })
    }
    Spacer(modifier = Modifier.height(16.dp))
}


@Composable
fun SkillCard(skill: Skill) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp,
        modifier = Modifier
            .wrapContentHeight()
            .size(width = 280.dp, height = 250.dp)
            .padding(end = 16.dp)
    )
    {
        Column(modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = {}
            )
        ) {
            Row {
                GlideImage(
                    imageModel = skill.imageUrl,
                    loading = { LoadingProgressBox() },
                    failure = { ErrorBox() },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(100.dp).fillMaxWidth()
                )
            }
            Column(modifier = Modifier.padding(16.dp)){
                Text(
                    text = skill.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = skill.description
                )
            }
        }
    }
}

@Composable
fun LoadingProgressBox() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.7f)){
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ErrorBox(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .background(Color.Red)
    ){
        Text(text = "Error loading resource")
    }
}

data class Skill(val name: String, val description: String,
                 val imageUrl: String)
val skillsList = listOf(
    Skill(
        "Android Development",
        "Focused on Native Android using Kotlin and the Jetpack suite",
        FIREBASE_BASE_URL + "o/android_card.png?alt=media"
    ),
    Skill(
        ".NET C#",
        "5 years experience developing billing and business management software for enterprise costumers",
        FIREBASE_BASE_URL + "o/dotnet.png?alt=media"
    ),
    Skill(
        "Cloud and Virtualization",
        "5 years experience with Docker and AWS products such as EC2, S3, RDS and ELB",
        FIREBASE_BASE_URL + "o/aws.png?alt=media"
        //"Applying cloud technology to increase uptime, reliability and security in software being developed"
    ),
    Skill(
        "Web Technologies",
        "2 years experience building a platform management system with Laravel",
        FIREBASE_BASE_URL + "o/webapi.jpg?alt=media"
        //"Created to better serve clients and easily manage core necessities, such as licensing or backups",
    ),
    Skill(
        "Software Engineering",
        "Daily contact with Project Management, Agile principles and VCS",
        FIREBASE_BASE_URL + "o/projectmanagement.png?alt=media"
        //"Own and take responsibility for my products and features",
    )
)