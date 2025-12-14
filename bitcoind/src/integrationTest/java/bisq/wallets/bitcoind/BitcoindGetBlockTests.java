package bisq.wallets.bitcoind;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;



import bisq.wallets.bitcoind.rpc.BitcoindDaemon;
import bisq.wallets.regtest.BitcoindExtension;
import bisq.wallets.regtest.bitcoind.BitcoindRegtestSetup;

@ExtendWith(BitcoindExtension.class)
public class BitcoindGetBlockTests {
    private final BitcoindDaemon daemon;

    public BitcoindGetBlockTests(BitcoindRegtestSetup regtestSetup) {
        this.daemon = regtestSetup.getDaemon();
    }

    @Test
    void blockVerbosityZeroTest() {
        String blockHash = daemon.getBestBlockHash();
        String serializedBlock = daemon.getBlockVerbosityZero(blockHash);
        assertThat(serializedBlock).isNotEmpty();
    }
}
