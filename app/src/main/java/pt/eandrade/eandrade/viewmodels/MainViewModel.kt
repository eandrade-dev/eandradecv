package pt.eandrade.eandrade.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import pt.eandrade.eandrade.components.experience.JobDetails
import pt.eandrade.eandrade.components.experience.JobExperience
import pt.eandrade.eandrade.utils.ApiService

class MainViewModel : ViewModel() {
    var aboutMeString: String by mutableStateOf("")
    var jobsData: List<JobExperience> by mutableStateOf(emptyList())
    var jobExperienceDetails: JobDetails by mutableStateOf(
        JobDetails("", "", "", "", emptyList(), emptyList(), emptyList())
    )

    fun getAboutMe() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            aboutMeString = try {
                apiService.getAboutMe()
            } catch (e: Exception) {
                "Failed to obtain 'About Me' section"
            }
        }
    }

    fun getAllJobs() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            jobsData = try {
                val jsonStr = apiService.getAllJobs()
                val listType = object : TypeToken<List<JobExperience>>(){}.type
                Gson().fromJson(jsonStr, listType)
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    fun getJobExperience(firebaseIdentifier: String) {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            jobExperienceDetails = try {
                val jsonStr = apiService.getJobDetails(firebaseIdentifier)
                Gson().fromJson(jsonStr, JobDetails::class.java)
            } catch (e: Exception) {
                JobDetails("", "", "", "", emptyList(), emptyList(), emptyList())
            }
        }
    }
}