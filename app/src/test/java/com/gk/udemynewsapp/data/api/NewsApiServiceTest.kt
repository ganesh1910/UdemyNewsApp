package com.gk.udemynewsapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiServiceTest {
    private lateinit var service: NewsApiService
    private lateinit var server: MockWebServer

    @Before
    fun setup() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExcepted() {
        runBlocking {
            enqueueMockResponse("newresponse.json")
            val responseBody = service.getTopHeadlines(country = "us", page = 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=0326e27a00d248f9a3f47f5ddfb65c1d")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize() {
        runBlocking {
            enqueueMockResponse("newresponse.json")
            val responseBody = service.getTopHeadlines(country = "us", page = 1).body()
            val articles = responseBody?.articles
            assertThat(articles?.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("newresponse.json")
            val responseBody = service.getTopHeadlines(country = "us", page = 1).body()
            val articles = responseBody!!.articles
            val article = articles[0]
            assertThat(article.author).isEqualTo("Eric Levenson, Celina Tebor, Kristina Sgueglia, Nouran Salahieh")
            assertThat(article.url).isEqualTo("https://www.cnn.com/2023/09/12/us/danelo-cavalcante-inmate-search-pennsylvania-tuesday/index.html")
            assertThat(article.publishedAt).isEqualTo("2023-09-12T14:28:00Z")
        }
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }
}