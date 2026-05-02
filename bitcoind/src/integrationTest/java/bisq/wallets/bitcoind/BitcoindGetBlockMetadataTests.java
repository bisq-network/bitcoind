package bisq.wallets.bitcoind;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;



import bisq.wallets.bitcoind.rpc.BitcoindDaemon;
import bisq.wallets.regtest.BitcoindExtension;
import bisq.wallets.regtest.bitcoind.BitcoindRegtestSetup;

@ExtendWith(BitcoindExtension.class)
public class BitcoindGetBlockMetadataTests {
    private final BitcoindDaemon daemon;

    public BitcoindGetBlockMetadataTests(BitcoindRegtestSetup regtestSetup) {
        this.daemon = regtestSetup.getDaemon();
    }

    @Test
    void blockCountTest() {
        int blockCount = daemon.getBlockCount();
        assertThat(blockCount).isGreaterThanOrEqualTo(101);
    }
}
