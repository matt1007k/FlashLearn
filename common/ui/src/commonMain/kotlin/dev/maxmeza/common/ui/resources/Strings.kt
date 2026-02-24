package dev.maxmeza.common.ui.resources

import androidx.compose.runtime.Composable
import dev.maxmeza.flashlearn.common.ui.resources.Res
import dev.maxmeza.flashlearn.common.ui.resources.app_name
import dev.maxmeza.flashlearn.common.ui.resources.home_goal_card_count
import dev.maxmeza.flashlearn.common.ui.resources.home_goal_progress
import dev.maxmeza.flashlearn.common.ui.resources.home_goal_title
import dev.maxmeza.flashlearn.common.ui.resources.home_recent_title
import dev.maxmeza.flashlearn.common.ui.resources.home_title
import dev.maxmeza.flashlearn.common.ui.resources.nav_add
import dev.maxmeza.flashlearn.common.ui.resources.nav_home
import dev.maxmeza.flashlearn.common.ui.resources.nav_profile
import dev.maxmeza.flashlearn.common.ui.resources.nav_stats
import dev.maxmeza.flashlearn.common.ui.resources.see_all
import dev.maxmeza.flashlearn.common.ui.resources.study_again
import dev.maxmeza.flashlearn.common.ui.resources.study_day
import dev.maxmeza.flashlearn.common.ui.resources.study_easy
import dev.maxmeza.flashlearn.common.ui.resources.study_good
import dev.maxmeza.flashlearn.common.ui.resources.study_hard
import dev.maxmeza.flashlearn.common.ui.resources.study_hour
import dev.maxmeza.flashlearn.common.ui.resources.study_min
import dev.maxmeza.flashlearn.common.ui.resources.study_progress
import dev.maxmeza.flashlearn.common.ui.resources.study_question
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


object Strings {
    val appName = Res.string.app_name
    val homeTitle = Res.string.home_title
    val homeGoalTitle = Res.string.home_goal_title
    val homeGoalCardCount = Res.string.home_goal_card_count
    val homeGoalProgress = Res.string.home_goal_progress
    val homeRecentTitle = Res.string.home_recent_title
    val seeAll = Res.string.see_all
    val navHome = Res.string.nav_home
    val navAdd = Res.string.nav_add
    val navStats = Res.string.nav_stats
    val navProfile = Res.string.nav_profile
    val studyProgress = Res.string.study_progress
    val studyQuestion = Res.string.study_question
    val studyMin = Res.string.study_min
    val studyHour = Res.string.study_hour
    val studyDay = Res.string.study_day
    val studyAgain = Res.string.study_again
    val studyHard = Res.string.study_hard
    val studyGood = Res.string.study_good
    val studyEasy = Res.string.study_easy
}
@Composable
fun homeGoalCardCountValue(count: Int): String {
    return stringResource(Res.string.home_goal_card_count, count)
}
