package com.rancic.development.demo.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rancic.development.demo.app.common.Error
import com.rancic.development.demo.app.common.Result
import com.rancic.development.demo.app.remote.model.Car
import com.rancic.development.demo.app.repository.CarRepository
import com.rancic.development.demo.app.ui.components.model.Category
import com.rancic.development.demo.app.ui.components.model.CategoryType
import com.rancic.development.demo.app.ui.navigation.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {

    private val _carsFlow = MutableStateFlow<Result<List<Car>>?>(null)
    val carsFlow: StateFlow<Result<List<Car>>?> = _carsFlow

    init {
        getCars(CategoryType.ALL.name.lowercase())
    }



    fun getCars(category: String) = viewModelScope.launch {
        repository.getCars(category)
            .onStart { _carsFlow.value = Result.loading() }
            .catch {
                _carsFlow.value = Result.error(Error(message = it.localizedMessage))
            }.collect { result ->
                result.let {
                    if (it.status == Result.Status.SUCCESS) {
                        it.data?.let { session ->
                            _carsFlow.value = Result.success(session)
                        }
                    } else if (it.status == Result.Status.ERROR) {
                        _carsFlow.value =
                            Result.error(error = it.error ?: Error.UNKNOWN_ERROR)
                    }
                }
            }
    }


}
