//package com.ryan.agriaid
//
//import android.content.Context
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.onRoot
//import androidx.compose.ui.test.printToLog
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.rememberNavController
//import androidx.test.core.app.ApplicationProvider
//import com.ryan.agriaid.data.ViewModelFactory
//import com.ryan.agriaid.data.local.user.User
//import com.ryan.agriaid.data.local.user.UserViewModel
//import com.ryan.agriaid.ui.screen.profile.ProfileScreen
//import com.ryan.agriaid.utility.NetworkUtils
//import com.ryan.agriaid.utility.PreferencesHelper
//import org.junit.Rule
//import org.junit.Test
//import io.mockk.every
//import io.mockk.mockkStatic
//import kotlinx.coroutines.runBlocking
//
//class MainScreenTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun testMainScreen() {
//        // Set up the content with termsAccepted set to true
//        composeTestRule.setContent {
//            // Simulasikan termsAccepted menjadi true di sini
//            val context = LocalContext.current
//            val preferencesHelper = PreferencesHelper(context)
//            preferencesHelper.setTermsAccepted(true)
//
//            MainScreen()
//        }
//        composeTestRule.onRoot().printToLog("DEBUG")
//        // Verifikasi UI pada tampilan utama
//        composeTestRule.onNodeWithText("Pendahuluan").assertIsDisplayed()
//    }
//
////    @Test
////    fun testNavigationToProfileScreen() {
////        composeTestRule.setContent {
////            MainScreen()
////        }
////
////        composeTestRule.onNodeWithText("Profile").performClick()
////
////        // Verifikasi tampilan ProfileScreen
////        composeTestRule.onNodeWithText("Profile Screen").assertIsDisplayed()
////    }
//
////    @Test
////    fun testButtonClick() {
////        composeTestRule.setContent {
////            MainScreen()
////        }
////
////        // Klik pada tombol tertentu
////        composeTestRule.onNodeWithText("Submit").performClick()
////
////        // Verifikasi hasil setelah klik tombol
////        composeTestRule.onNodeWithText("Submitted").assertIsDisplayed()
////    }
//
////    @Test
////    fun testStateChange() {
////        val context = ApplicationProvider.getApplicationContext<Context>()
////        val viewModelFactory = ViewModelFactory.getInstance(context)
////        val userViewModel: UserViewModel = viewModel(factory = viewModelFactory)
////
////        composeTestRule.setContent {
////            ProfileScreen(navController = rememberNavController(), user = null, isLogin = false, onClick = {})
////        }
////
////        // Simulasikan perubahan state
////        userViewModel.saveUser(User(1, "Riyan", "Admin", "https://st3.depositphotos.com/15648834/17930/v/450/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"))
////
////        // Verifikasi perubahan state di UI
////        composeTestRule.onNodeWithText("Riyan").assertIsDisplayed()
////    }
//
////    @Test
////    fun testOfflineMode() {
////        val context = ApplicationProvider.getApplicationContext<Context>()
////
////        // Simulasikan offline mode
////        NetworkUtils.setNetworkAvailable(context, false)
////
////        composeTestRule.setContent {
////            MainScreen()
////        }
////
////        // Verifikasi pesan offline muncul
////        composeTestRule.onNodeWithText("Anda sedang luring, beberapa fungsi tidak tersedia").assertIsDisplayed()
////    }
//}