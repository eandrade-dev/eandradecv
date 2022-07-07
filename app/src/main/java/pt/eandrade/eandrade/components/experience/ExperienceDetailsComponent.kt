package pt.eandrade.eandrade.components.experience

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import pt.eandrade.eandrade.FIREBASE_BASE_URL
import pt.eandrade.eandrade.R
import pt.eandrade.eandrade.components.ErrorBox
import pt.eandrade.eandrade.components.LoadingProgressBox
import pt.eandrade.eandrade.ui.theme.EAndradeTheme
import pt.eandrade.eandrade.viewmodels.MainViewModel

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ExperienceDetailsComponent(navController: NavHostController, firebaseDataIdentifier: String,
                               mainViewModel: MainViewModel = viewModel()) {
    mainViewModel.getJobExperience(firebaseDataIdentifier)
    val jobDetails = mainViewModel.jobExperienceDetails

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    EAndradeTheme {
        BottomSheetScaffold(
            content = {
                ExperienceDetailsHeader(navController, jobDetails)
            },
            sheetContent = {
                ExperienceDetailsInfo(jobDetails)
            },
            sheetShape = RoundedCornerShape(24.dp),
            scaffoldState = bottomSheetScaffoldState,
            // There is currently a glitch that automatically expands this section
            // because sheetPeekHeight is set to 520.dp. Setting the value to a lower number
            // will cause the sheet to stop automatically expanding
            sheetPeekHeight = if (sheetState.isAnimationRunning || sheetState.isVisible) {
                0.dp
            }else {
                520.dp
            },

        )
    }

}

@Composable
fun ExperienceDetailsHeader(
    navController: NavHostController,
    jobDetails: JobDetails
){
    Column(modifier = Modifier
        .background(MaterialTheme.colors.primaryVariant)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Experience", style = typography.h5)
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "Close",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() },
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 96.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ){
                GlideImage(
                    imageModel = FIREBASE_BASE_URL + jobDetails.logo,
                    loading = { LoadingProgressBox() },
                    failure = { ErrorBox() },
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)
                )
            }
            Text(
                text = jobDetails.employer,
                style = typography.h5,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = jobDetails.title,
                style = typography.h6,
            )
            Text(
                text = jobDetails.timeframe,
                style = typography.h6
            )
        }
    }
}

@Composable
fun ExperienceDetailsInfo(
    jobDetails: JobDetails
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Notable work",
            style = typography.h5,
            modifier = Modifier
                .padding(top = 24.dp, start = 24.dp)
        )
        Column(
            modifier = Modifier
                .padding(start = 64.dp, end = 64.dp)
        ) {
            for ((index) in jobDetails.work.withIndex()) {
                Column(Modifier.padding(vertical = 16.dp)) {
                    Text(
                        text = jobDetails.workHeaders[index],
                        color = MaterialTheme.colors.primaryVariant,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = jobDetails.work[index]
                    )
                }
            }
        }
        Text(
            text = "Skills practiced",
            style = typography.h5,
            modifier = Modifier
                .padding(top = 16.dp, start = 24.dp)
        )
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 64.dp)
        ) {
            for (skill in jobDetails.skills) {
                Text(
                    text = skill
                )
            }
        }
    }
}

data class JobExperience(val title: String, val employer: String, val timeframe: String,
                         val category: String,  val logo: String, val firebaseDataIdentifier: String
)

data class JobDetails(val title: String, val employer: String, val timeframe: String, val logo: String,
                      val workHeaders: List<String>, val work: List<String>, val skills: List<String>)