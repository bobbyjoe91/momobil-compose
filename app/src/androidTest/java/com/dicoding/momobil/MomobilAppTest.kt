package com.dicoding.momobil

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import com.dicoding.momobil.data.FakeProductList
import com.dicoding.momobil.navigation.Screen
import com.dicoding.momobil.ui.theme.MomobilTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MomobilAppTest {
  @get:Rule
  val testRule = createAndroidComposeRule<ComponentActivity>()
  private lateinit var navigation: TestNavHostController

  @Before
  fun setUp() {
    testRule.setContent {
      MomobilTheme {
        navigation = TestNavHostController(LocalContext.current)
        navigation.navigatorProvider.addNavigator(ComposeNavigator())
        MomobilApp(navController = navigation)
      }
    }
  }

  @Test
  fun navHost_verifyLandingPage() {
    navigation.assertCurrentRouteName(Screen.LandingPage.routeName)
  }

  @Test
  fun navHost_openDrawer_navigateToAbout() {
    testRule.onNodeWithContentDescription("toggle_drawer").performClick()
    testRule.onNodeWithContentDescription("about_page")
      .assertExists()
      .performClick()

    navigation.assertCurrentRouteName(Screen.About.routeName)
  }

  @Test
  fun navHost_navigateToCart() {
    testRule.onNodeWithContentDescription("cart_page")
      .assertExists()
      .performClick()

    navigation.assertCurrentRouteName(Screen.Cart.routeName)
  }

  @Test
  fun navHost_selectProduct_navigateToProductDetail_buyProduct_chooseAnotherProduct_clearCart() {
    navigation.assertCurrentRouteName(Screen.LandingPage.routeName)

    testRule.waitUntil(12000L) {
      testRule
        .onAllNodesWithContentDescription("product_list")
        .fetchSemanticsNodes().size == 1
    }

    val testIndices = listOf(10, 15)

    for (index in testIndices) {
      testRule
        .onNodeWithContentDescription("product_list")
        .performScrollToIndex(index)
      testRule
        .onNodeWithText(FakeProductList.mobilList[index].name)
        .performClick()

      navigation.assertCurrentRouteName(Screen.ProductDetail.routeName)

      testRule.waitUntil(3000L) {
        testRule
          .onAllNodesWithText(FakeProductList.mobilList[index].name)
          .fetchSemanticsNodes().size == 1
      }

      testRule
        .onNodeWithText(FakeProductList.mobilList[index].name)
        .assertIsDisplayed()

      testRule.onNodeWithContentDescription("buy_now").performScrollTo()
      testRule.onNodeWithContentDescription("buy_now").performClick()

      navigation.assertCurrentRouteName(Screen.Cart.routeName)

      testRule.waitUntil(3000L) {
        testRule
          .onAllNodesWithTag("cart_item")
          .fetchSemanticsNodes().isNotEmpty()
      }

      Espresso.pressBack()
      navigation.assertCurrentRouteName(Screen.ProductDetail.routeName)
      Espresso.pressBack()
      navigation.assertCurrentRouteName(Screen.LandingPage.routeName)
    }

    testRule.onNodeWithContentDescription("cart_page")
      .assertExists()
      .performClick()

    testRule.onAllNodesWithTag("cart_item").assertCountEquals(2)
  }
}