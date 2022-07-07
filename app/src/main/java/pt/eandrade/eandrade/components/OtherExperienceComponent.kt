package pt.eandrade.eandrade.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pt.eandrade.eandrade.viewmodels.MainViewModel

@Composable
@ExperimentalMaterialApi
fun OtherExperienceComponent(navController: NavHostController, mainViewModel: MainViewModel = viewModel()){
    Text(
        text = "Other Experience",
        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp),
        style = MaterialTheme.typography.h5
    )

    val jobs = mainViewModel.jobsData.filter { it.category == "other" }
    for(job in jobs){
        JobRowComponent(navController, job)
    }
}