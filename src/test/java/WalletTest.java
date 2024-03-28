import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WalletTest {
    private Wallet wallet1;
    private Wallet wallet2;

    @BeforeEach
    public void setUp() {
        wallet1 = new Wallet(1000, "USD");
        wallet2 = new Wallet(500, "USD");
    }

    @Test
    public void testDepositAmount() {
        wallet1.depositAmount(500);
        wallet1.depositAmount(1000);
        assertEquals(2500, wallet1.getBalance());
    }

    @Test
    public void testCurrency() {
        assertEquals("IDR", wallet2.getCurrency());
    }

    @Test
    public void testWithdrawAmount() {
        assertTrue(wallet1.withdrawAmount(150));
        assertEquals(850, wallet1.getBalance());
    }

    @Test
    public void testWithdrawAmountInsufficientFunds() {
        assertFalse(wallet1.withdrawAmount(150.0));
        assertEquals(100.0, wallet1.getBalance());
    }

    @Test
    public void testTransferFundsSufficientFunds() {
        Wallet recipient = new Wallet(0, "USD");
        wallet1.transferFunds(recipient, 750);
        assertEquals(250, wallet1.getBalance());
        assertEquals(750, recipient.getBalance());
    }

    @Test
    public void testTransferFundsInsufficientFunds() {
        Wallet recipient = new Wallet(0, "USD");
        assertThrows(IllegalArgumentException.class, () -> wallet1.transferFunds(recipient, 0));
        assertEquals(100, wallet1.getBalance());
        assertEquals(0, recipient.getBalance());
    }

    @Test
    public void testTransferFundsDifferentCurrency() {
        Wallet recipient = new Wallet(0, "EUR");
        assertThrows(IllegalArgumentException.class, () -> wallet1.transferFunds(recipient, 250));
        assertEquals(750, wallet1.getBalance());
        assertEquals(0, recipient.getBalance());
    }
}
