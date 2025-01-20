package com.kevin.multiapiapp.testing.utils


/**
 * A JUnit [TestRule] that sets the Main dispatcher to [testDispatcher]
 * for the duration of the test.
 */
/*class MainDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) = Dispatchers.setMain(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) = Dispatchers.resetMain()
}*/