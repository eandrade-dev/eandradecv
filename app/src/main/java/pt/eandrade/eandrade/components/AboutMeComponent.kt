package pt.eandrade.eandrade.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skydoves.landscapist.glide.GlideImage
import pt.eandrade.eandrade.FIREBASE_BASE_URL
import pt.eandrade.eandrade.viewmodels.MainViewModel
import pt.eandrade.eandrade.ui.theme.Shapes

@Composable
@ExperimentalMaterialApi
fun AboutMeComponent(mainViewModel: MainViewModel = viewModel()) {
    mainViewModel.getAboutMe()

    val descriptionFontSize = MaterialTheme.typography.subtitle1.fontSize
    val descriptionFontWeight = FontWeight.Normal
    val descriptionMaxLines= 4
    val shape = Shapes.medium
    val padding = 12.dp

    Text(
        text = "About Me",
        modifier = Modifier.padding(bottom = 8.dp),
        style = MaterialTheme.typography.h5
    )

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            if (expandedState) {
                Text(
                    text = mainViewModel.aboutMeString,
                    fontSize = descriptionFontSize,
                    fontWeight = descriptionFontWeight,
                )
                GlideImage(
                    imageModel = FIREBASE_BASE_URL + "o/cats.png?alt=media",
                    loading = { LoadingProgressBox() },
                    failure = { ErrorBox() },
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
                )
            }else{
                Text(
                    text = mainViewModel.aboutMeString,
                    fontSize = descriptionFontSize,
                    fontWeight = descriptionFontWeight,
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
        }
    }
}