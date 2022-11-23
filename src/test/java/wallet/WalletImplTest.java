package wallet;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WalletImplTest {

    @Test
    public void testSpend() {
        String filename = "C:\\Users\\hp\\Desktop\\temp\\input.csv";
        Long amount = 150L;

        WalletImpl walletImpl = new WalletImpl();
        walletImpl.load(filename);
        Long currentBalance = walletImpl.getBalance();
        SpendSummary spendSummary = walletImpl.spend(amount);

        Long expectedChange = 151L;
        List<Long> expectedCoinsSpend = Arrays.stream(new Long[] {1L, 100L, 200L}).collect(Collectors.toList());
        SpendSummary expectedSummary =
                SpendSummary.builder()
                        .change(expectedChange)
                        .coinsSpend(expectedCoinsSpend).build();

        Assert.assertEquals(expectedSummary, spendSummary);
        Assert.assertTrue(walletImpl.peek().contains(expectedChange));
        Assert.assertEquals(Long.valueOf(currentBalance - amount), walletImpl.getBalance());
    }

    @Test
    public void testLoadShouldReadFileSuccessful() {
        String filename = "C:\\Users\\hp\\Desktop\\temp\\input.csv";

        WalletImpl walletImpl = new WalletImpl();
        walletImpl.load(filename);

        Assert.assertEquals(6, walletImpl.peek().size());
    }

    @Test
    public void testBalanceShouldReturnTheProperBalance() {
        String filename = "C:\\Users\\hp\\Desktop\\temp\\input.csv";

        WalletImpl walletImpl = new WalletImpl();
        walletImpl.load(filename);
        Long currentBalance = walletImpl.getBalance();

        Long expectedBalance = 1502L;
        Assert.assertEquals(expectedBalance, currentBalance);
    }
}