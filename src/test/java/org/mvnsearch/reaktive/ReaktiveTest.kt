package org.mvnsearch.reaktive

import com.badoo.reaktive.single.singleOf
import com.badoo.reaktive.single.subscribe
import org.junit.Test

/**
 * reaktive test
 *
 * @author linux_china
 */
class ReaktiveTest {

    @Test
    fun testSpike() {
        val first = singleOf("First")
        first.subscribe {
            println(it)
        }
        Thread.sleep(1000)
    }
}