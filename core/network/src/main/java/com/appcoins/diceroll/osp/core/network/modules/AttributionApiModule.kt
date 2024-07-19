package com.appcoins.diceroll.osp.core.network.modules

import com.appcoins.diceroll.osp.core.network.annotations.AttributionRetrofitClient
import com.appcoins.diceroll.osp.core.network.annotations.HttpClient
import com.appcoins.diceroll.osp.core.network.modules.api.AttributionApi
import com.appcoins.diceroll.osp.core.utils.attributionUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AttributionApiModule {

  @Singleton
  @Provides
  @AttributionRetrofitClient
  fun provideAttributionRetrofit(@HttpClient client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(attributionUrl)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton
  @Provides
  fun provideAttributionApi(@AttributionRetrofitClient retrofit: Retrofit): AttributionApi {
    return retrofit.create(AttributionApi::class.java)
  }
}