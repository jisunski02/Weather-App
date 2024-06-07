package dev.jayson.weatherapp.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import dev.jayson.weatherapp.data.model.UserData
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
@RunWith(AndroidJUnit4::class)
class UserDataDaoTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userDataDao: UserDataDao
    private lateinit var weatherRoomDatabase: WeatherRoomDatabase

    @Before
    fun setUp() {
        weatherRoomDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherRoomDatabase::class.java).build()

        userDataDao = weatherRoomDatabase.getUserDataDao()
    }

    @After
    fun tearDown() {
        weatherRoomDatabase.close()
    }

    @Test
    fun saveUserDataTest() = runBlocking {
        val userData = UserData(1, "username1", "userpassword1")

        userDataDao.registerUser(userData)

        val getUser = userDataDao.loginUser("username1", "userpassword1")

        getUser.collect{
            //assertThat(userData.username).is(it.username)
            Truth.assertThat(userData.username).isEqualTo(it.username)
        }
    }
}