package wallet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

class WalletImpl implements Wallet {
    private List<Long> coins = new LinkedList<>();

    @Override
    public SpendSummary spend(Long amount) {
        List<Long> coinsSpend = new ArrayList<>();
        long sum=0;
        Iterator<Long> it = coins.iterator();
        while (it.hasNext()) {
            Long coin = it.next();

            if (sum < amount) {
                sum += coin;
                coinsSpend.add(coin);
                it.remove();
            } else break;
        }

        Long change = sum - amount;
        coins.add(change);

        return SpendSummary.builder().coinsSpend(coinsSpend).change(change).build();
    }

    @Override
    public void load(String filename) {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] coinsInput = line.split(",");
                Stream.of(coinsInput).forEach(s -> coins.add(Long.valueOf(s)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getBalance() {
        return coins.stream().reduce((aLong, aLong2) -> aLong+aLong2).orElse(0L);
    }

    @Override
    public Collection<Long> peek() {
        return coins;
    }
}



