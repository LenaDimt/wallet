package wallet;

import java.util.Collection;

interface Wallet {

    SpendSummary spend(Long amount);

// Iteration 1

    /**
     * Load money into my wallet (fixed sized denominations of any kind are acceptable)
     */
    void load(String filename);

    /**
     * Return current balance of my wallet
     */
    Long getBalance();

    /**
     * Return the coins in the wallet.
     * (peek does NOT need to be performant as to be used solely for testing)
     */
    Collection<Long> peek();
}
