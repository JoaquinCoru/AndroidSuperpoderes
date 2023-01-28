package com.joaquinco.marvelapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import com.joaquinco.marvelapp.ui.components.ItemCharacter
import org.junit.Rule
import org.junit.Test

class CharacterItemTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun givenCharacterItemWithFavoriteFalseCheckWhiteHeartDisplayed() {
        // GIVEN
        composeRule.setContent {
            ItemCharacter("3D-Man", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/landscape_xlarge.jpg", false)
        }
        Thread.sleep(3000)
        // WHEN

        // THEN
        composeRule.onNodeWithTag(R.drawable.white_heart.toString()).assertIsDisplayed()
    }

    @Test
    fun givenCharacterItemWithFavoriteTrueCheckRedHeartDisplayed() {
        // GIVEN
        composeRule.setContent {
            ItemCharacter("3D-Man", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/landscape_xlarge.jpg", true)
        }
        Thread.sleep(3000)
        // WHEN

        // THEN
        composeRule.onNodeWithTag(R.drawable.red_heart.toString()).assertIsDisplayed()
    }


}