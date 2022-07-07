package pt.eandrade.eandrade.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import pt.eandrade.eandrade.FIREBASE_BASE_URL
import pt.eandrade.eandrade.MainActivity.Companion.NAV_EXPERIENCE_DETAILS_ROUTE
import pt.eandrade.eandrade.components.experience.JobExperience
import pt.eandrade.eandrade.viewmodels.MainViewModel

@Composable
@ExperimentalMaterialApi
fun JobExperienceComponent(navController: NavController, mainViewModel: MainViewModel = viewModel()) {
    Text(
        text = "Job Experience",
        modifier = Modifier.padding(bottom = 8.dp),
        style = typography.h5
    )

    val jobs = mainViewModel.jobsData.filter { it.category == "job" }
    for(job in jobs){
        JobRowComponent(navController, job)
    }
}

@Composable
fun JobRowComponent(navController: NavController, jobExperience: JobExperience,
                    mainViewModel: MainViewModel = viewModel()){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),

        ){
        Column {
            GlideImage(
                imageModel = FIREBASE_BASE_URL + jobExperience.logo,
                loading = { LoadingProgressBox() },
                failure = { ErrorBox() },
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxHeight()
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = jobExperience.employer,
                style = typography.h6,
            )
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = jobExperience.title, style = typography.subtitle1)
        Text(text = jobExperience.timeframe, style = typography.subtitle2)
    }
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){
        TextButton(
            onClick = {
                navController.navigate(
                    "$NAV_EXPERIENCE_DETAILS_ROUTE/${jobExperience.firebaseDataIdentifier}"
                )
            },
        ) {
            Text(text = "More info")
        }
    }
}