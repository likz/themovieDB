package com.weblib.movieapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

class PopularServiceTest {

    fun filePath(fileName: String) = "response/$fileName"

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer

    @Throws(IOException::class)
    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    @Throws(IOException::class)
    private fun enqueueResponse() {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(filePath("populars.json"))
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }

    private fun createService(clazz: Class<PopularApi>) = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(clazz)

    private lateinit var service: PopularApi

    @Throws(IOException::class)
    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        this.service = createService(PopularApi::class.java)
    }

    @Throws(IOException::class)
    @Test
    suspend fun `get all popular test`() {
        enqueueResponse()
        val response = this.service.getAllPopulars()
        Assert.assertEquals(0L, 0L)
        //TODO
    }
}