package ru.mamzin.mygym2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mamzin.mygym2.database.ExerciseDataBase
import ru.mamzin.mygym2.model.Exercise
import java.util.*

class ExerciseViewModel (application: Application): AndroidViewModel(application) {

    val allExercise: LiveData<MutableList<Exercise>>
    private val repository: ExerciseRepository

    init {
        val dao = ExerciseDataBase.getDatabase(application).exerciseDao()
        repository = ExerciseRepository(dao)
        allExercise = repository.allExercise
    }

    fun addExercise(exercise: Exercise) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(exercise)
    }

    fun updateExercise(exercise: Exercise) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(exercise)
    }

    fun deleteExercise(exercise: Exercise) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(exercise)
    }

}