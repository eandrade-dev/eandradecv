package pt.eandrade.eandrade.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import pt.eandrade.eandrade.FIREBASE_BASE_URL
import pt.eandrade.eandrade.MainActivity.Companion.NAV_CURRICULUM_ROUTE
import pt.eandrade.eandrade.R

@Composable
@ExperimentalMaterialApi
fun OnboardingScreen(navController: NavHostController) {
    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(top = 48.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.name),
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = stringResource(R.string.job_position),
                    style = MaterialTheme.typography.h6.copy(
                        textAlign = TextAlign.Center
                    )
                )
                Column(
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .size(192.dp)
                        .clip(CircleShape)
                ) {
                    GlideImage(
                        imageModel = FIREBASE_BASE_URL + "o/foto2.png?alt=media",
                        contentScale = ContentScale.Crop,
                        placeHolder = ImageBitmap
                            .imageResource(R.drawable.accountcircle),
                        error = ImageBitmap
                            .imageResource(R.drawable.accountcircle),
                        previewPlaceholder = R.drawable.accountcircle
                    )
                }
                ContactsComponent(onboarding = true)
            }
            Button(
                modifier = Modifier
                    .animateContentSize()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 64.dp)
                    .height(56.dp)
                    .clip(CircleShape),
                onClick = { navController.navigate(NAV_CURRICULUM_ROUTE) }
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "Enter"
                )
                Text("Learn More")
            }
        }
    }
}