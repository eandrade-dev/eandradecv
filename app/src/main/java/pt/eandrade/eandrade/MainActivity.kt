@file:OptIn(ExperimentalMaterialApi::class)

package pt.eandrade.eandrade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skydoves.landscapist.glide.GlideImage
import pt.eandrade.eandrade.MainActivity.Companion.NAV_CURRICULUM_ROUTE
import pt.eandrade.eandrade.MainActivity.Companion.NAV_EXPERIENCE_DETAILS_ROUTE
import pt.eandrade.eandrade.MainActivity.Companion.NAV_LICENSES_ROUTE
import pt.eandrade.eandrade.MainActivity.Companion.NAV_ONBOARDING_ROUTE
import pt.eandrade.eandrade.components.*
import pt.eandrade.eandrade.components.experience.ExperienceDetailsComponent
import pt.eandrade.eandrade.ui.theme.EAndradeTheme
import pt.eandrade.eandrade.viewmodels.MainViewModel

const val FIREBASE_BASE_URL = "https://firebasestorage.googleapis.com/v0/b/eandradecv.appspot.com/"
const val initialImageFloat = 170f

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EAndradeTheme {
                CurriculumApp()
            }
        }
    }

    companion object {
        const val NAV_ONBOARDING_ROUTE = "NavOnboardingRoute"
        const val NAV_CURRICULUM_ROUTE = "NavCurriculumRoute"
        const val NAV_EXPERIENCE_DETAILS_ROUTE = "NavExperienceDetailsRoute"
        const val NAV_LICENSES_ROUTE = "NavLicensesRoute"
    }
}

@Composable
private fun CurriculumApp() {
    val navController = rememberNavController()
    EAndradeTheme {
        Scaffold {
            CurriculumNavHost(navController)
        }
    }
}

@Composable
fun CurriculumNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NAV_ONBOARDING_ROUTE) {
        composable(NAV_ONBOARDING_ROUTE) { OnboardingScreen(navController) }
        composable(NAV_CURRICULUM_ROUTE) { CurriculumScreen(navController) }
        composable(
            "$NAV_EXPERIENCE_DETAILS_ROUTE/{firebaseDataIdentifier}",
            arguments = listOf(
                navArgument("firebaseDataIdentifier"){
                    type = NavType.StringType
                })
        ) { entry ->
            val firebaseIdentifier = entry.arguments?.getString("firebaseDataIdentifier")
            ExperienceDetailsComponent(
                navController,
                firebaseIdentifier!!
            )
        }
        composable(NAV_LICENSES_ROUTE) { LicensesDetailsComponent(navController) }
    }
}

@Composable
private fun CurriculumScreen(navController: NavHostController) {
    val scrollState = rememberScrollState(0)
    val mainViewModel: MainViewModel = viewModel()
    remember { mainViewModel.getAllJobs() }

    EAndradeTheme {
        Scaffold(
            topBar = {
                TopAppBarView(scrollState.value.toFloat())
                TopProfileInfo(scrollState)
            }
        ) { padding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding()))
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = scrollState)
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    SocialsComponent()
                    SkillsComponent()
                    JobExperienceComponent(navController)
                    EducationComponent()
                    OtherExperienceComponent(navController)
                    AboutMeComponent()
                    ContactsComponent(onboarding = false)
                    LicensesComponent(navController)
                }
            }
        }
    }
}

@Composable
fun TopAppBarView(scroll: Float) {
    AnimatedVisibility(
        visible = scroll > initialImageFloat + 5,
        enter = slideInVertically(initialOffsetY = { -40 } ),
        exit = fadeOut(),
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.name))
            },
            navigationIcon = {
                ProfilePicture(32f)
            }
        )
    }
}

@Composable
fun TopProfileInfo(scrollState: ScrollState) {
    val visibilityChangeFloat = scrollState.value > initialImageFloat - 20
    if(scrollState.value.toFloat() < initialImageFloat)
    {
        Row {
            val dynamicAnimationSizeValue = (initialImageFloat
                    - scrollState.value.toFloat())
                .coerceIn(36f, initialImageFloat)
            ProfilePicture(size = dynamicAnimationSizeValue)
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 48.dp)
                    .alpha(animateFloatAsState(if (visibilityChangeFloat) 0f else 1f).value)
            ) {
                Text(
                    text = stringResource(R.string.name),
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(R.string.job_position),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp
                    ),
                )
            }
        }
    }

}

@Composable
fun ProfilePicture(size: Float){
    GlideImage(
        imageModel = FIREBASE_BASE_URL + "o/foto2.png?alt=media",
        contentScale = ContentScale.Crop,
        placeHolder = ImageBitmap.imageResource(R.drawable.accountcircle),
        error = ImageBitmap.imageResource(R.drawable.accountcircle),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .size(animateDpAsState(Dp(size)).value)
            .clip(CircleShape)
    )
}