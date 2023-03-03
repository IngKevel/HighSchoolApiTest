package com.chilate.nychighschoolsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chilate.nychighschoolsapp.feature_highschools.data.remote.HighSchoolApi
import com.chilate.nychighschoolsapp.feature_highschools.data.repository.HighSchoolRepositoryImpl
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

/**
 * Created by Kevel on 3/2/2023.
 */

class HighSchoolRepositoryTest {
    private val mockWebServer = MockWebServer().apply {
        url("/")
        dispatcher = myDispatcher
    }

    private val highSchoolApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HighSchoolApi::class.java)

    private val testRepository = HighSchoolRepositoryImpl(highSchoolApi)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `HighSchool List is obtained correctly`() {
        runBlocking {
            val highSchoolList = testRepository.getHighSchoolList()

            assertEquals(1, highSchoolList.data?.size)
        }
    }

    @Test
    fun `HighSchool Details is obtained correctly`() {
        runBlocking {
            val highSchoolDetails = testRepository.getSelectedHighSchool("00000")

            assertEquals("Test HighSchool", highSchoolDetails.data?.first()?.school_name)
            assertEquals("00000", highSchoolDetails.data?.first()?.dbn)
        }
    }
}

val myDispatcher: Dispatcher = object: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/s3k6-pzi2.json" -> MockResponse().apply { addResponse("high_school_list.json") }
            "/f9bf-2cp4.json?dbn=00000" -> MockResponse().apply { addResponse("high_school_list.json") }
            else -> MockResponse().setResponseCode(404)
        }
    }
}

fun MockResponse.addResponse(filePath: String): MockResponse {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        setResponseCode(200)
        setBody(it.readString(StandardCharsets.UTF_8))
    }
    return this
}