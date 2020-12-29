package com.appmobil.apprepair.data.repository

import android.app.DownloadManager
import com.appmobil.apprepair.data.model.ActionState
import com.appmobil.apprepair.data.model.News
import com.appmobil.apprepair.data.remote.NewsService
import com.appmobil.apprepair.data.remote.RetrofitApi
import retrofit2.await
import java.lang.Exception

class NewsRepository {
    private val newsService: NewsService by lazy { RetrofitApi.newsService() }

    suspend fun listNews(): ActionState<List<News>> {
        return try {
            val list = newsService.listNews().await()
            ActionState(list.data)
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccsess = false)
        }
    }

    suspend fun detailNews(url: String): ActionState<News> {
        return try {
            val list = newsService.detailNews(url).await()
            ActionState(list.data.first())
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccsess = false)
        }
    }

    suspend fun searchNews(query: String) : ActionState<List<News>> {
        return try {
            val list = newsService.searchNews(query).await()
            ActionState(list.data)
        } catch (e: Exception) {
            ActionState (message = e.message, isSuccsess = false)
        }
    }
}