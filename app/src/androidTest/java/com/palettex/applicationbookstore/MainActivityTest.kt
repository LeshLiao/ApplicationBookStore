package com.palettex.applicationbookstore

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testFilterPrice() {

        composeTestRule.onNodeWithTag("tesTag_price")
            .performTextClearance()

        composeTestRule.onNodeWithTag("tesTag_price")
            .performTextInput("16")

        composeTestRule.onNodeWithTag("tesTag_filter")
            .performClick()

        composeTestRule.onNodeWithText("BookA").assertIsDisplayed()
        composeTestRule.onNodeWithText("BookB").assertIsDisplayed()
        composeTestRule.onNodeWithText("BookC").assertDoesNotExist()
    }

    @Test
    fun testSearchBooks() {

        composeTestRule.onNodeWithTag("tesTag_keyWord")
            .performTextClearance()

        composeTestRule.onNodeWithTag("tesTag_keyWord")
            .performTextInput("A")

        composeTestRule.onNodeWithText("BookA").assertIsDisplayed()
        composeTestRule.onNodeWithText("BookB").assertDoesNotExist()
        composeTestRule.onNodeWithText("BookC").assertDoesNotExist()
    }
}