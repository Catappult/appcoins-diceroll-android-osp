package com.appcoins.diceroll.osp.feature.settings.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcoins.diceroll.osp.feature.settings.data.model.ThemeConfig
import com.appcoins.diceroll.osp.feature.settings.data.model.UserPrefs
import com.appcoins.diceroll.osp.feature.settings.data.repository.StoreDeeplinkRepository
import com.appcoins.diceroll.osp.feature.settings.data.repository.UserPrefsDataSource
import com.appcoins.diceroll.osp.feature.settings.ui.SettingsUiState.Loading
import com.appcoins.diceroll.osp.feature.settings.ui.SettingsUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  private val userPrefsDataSource: UserPrefsDataSource,
  private val storeDeeplinkRepository: StoreDeeplinkRepository,
) : ViewModel() {
  val uiState: StateFlow<SettingsUiState> =
    userPrefsDataSource.getUserPrefs()
      .map { userPrefs ->
        Success(userPrefs)
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Loading,
      )

  private val _storeDeeplinkState =
    MutableStateFlow<StoreDeeplinkUiState>(StoreDeeplinkUiState.Loading)
  internal val storeDeeplinkState: StateFlow<StoreDeeplinkUiState> get() = _storeDeeplinkState

  var shouldLaunchDeeplink = mutableStateOf(true)

  fun getUpdateDeeplink(appPackage: String, storePackage: String?) {
    viewModelScope.launch {
      storeDeeplinkRepository.getStoreDeeplinkUrl(appPackage, storePackage)
        .onSuccess { storeDeeplink ->
          _storeDeeplinkState.value = StoreDeeplinkUiState.Success(storeDeeplink.url)
        }
        .onFailure {
          _storeDeeplinkState.value = StoreDeeplinkUiState.Error
        }
    }
  }

  fun updateThemeConfig(themeConfig: ThemeConfig) {
    viewModelScope.launch {
      userPrefsDataSource.saveThemeConfig(themeConfig)
    }
  }
}

sealed interface SettingsUiState {
  data object Loading : SettingsUiState
  data class Success(val userPrefs: UserPrefs) : SettingsUiState
}

sealed interface StoreDeeplinkUiState {
  data object Loading : StoreDeeplinkUiState
  data object Error : StoreDeeplinkUiState
  data class Success(val storeDeeplinkUrl: String) : StoreDeeplinkUiState
}