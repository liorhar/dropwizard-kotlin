package liorh.dwkotlin

import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import io.dropwizard.testing.junit5.DropwizardAppExtension
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport
import org.junit.ClassRule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertNotNull


@ExtendWith(DropwizardExtensionsSupport::class)
class AppTest {
    @ClassRule
    val app = DropwizardAppExtension<AppConfig>(App::class.java)

    @Test
    fun testAppisUp() {
        assertNotNull(app.localPort)
    }
}
