import app.cash.turbine.test
import com.xero.interview.common.models.BankAccountModel
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.use_case.account_record.CountAccountRecordUseCase
import com.xero.interview.data.domain.use_case.bank_account.GetAllBankAccountsUseCase
import com.xero.interview.data.domain.use_case.bank_account.GetSumBankAccountUseCase
import com.xero.interview.home.MainDispatcherRule
import com.xero.interview.home.viewmodel.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class HomeViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val backAccountsUseCase = mock(GetAllBankAccountsUseCase::class.java)
    private val getSumBankAccountUseCase = mock(GetSumBankAccountUseCase::class.java)
    private val countAccountRecordUseCase = mock(CountAccountRecordUseCase::class.java)
    private val viewModel by lazy {
        HomeViewModel(
            backAccountsUseCase,
            getSumBankAccountUseCase,
            countAccountRecordUseCase,
            testDispatcher
        )
    }

    @Before
    fun setup() = runTest {
        `when`(backAccountsUseCase()).thenReturn(flowOf(bankAccounts))
        `when`(getSumBankAccountUseCase()).thenReturn(300.00)
        `when`(countAccountRecordUseCase(anyInt())).thenReturn(0)
    }

    @Test
    fun `loadBankAccounts() should get all BankAccountRecords`() = runTest {
        viewModel
        viewModel.bankAccounts.test {
            advanceUntilIdle()
            Assert.assertEquals(
                accModels,
                awaitItem()
            )
        }
    }

    private val bankAccounts = listOf<BankAccount>(
        BankAccount(100, 1, "Amana Bank NZ", -10000.00, -20000.00, "20 Oct 2023"),
        BankAccount(200, 2, "Common Wealth Bank", 6850.00, 6850.00, "20 Oct 2023"),
        BankAccount(300, 3, "Bank of Ceylon", 92345.12, 92345.12, "20 Oct 2023"),
    )

    private val accModels = listOf(
        BankAccountModel(bankAccounts[0]),
        BankAccountModel(bankAccounts[1]),
        BankAccountModel(bankAccounts[2])
    )
}