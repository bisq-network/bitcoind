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

    @Test
    void blockHashTest() {
        int blockCount = daemon.getBlockCount();
        String blockHash = daemon.getBlockHash(blockCount);

        assertThat(blockHash).isNotEmpty();
        assertThat(blockHash).hasSize(64);
    }

    @Test
    void bestBlockHashTest() {
        String blockHash = daemon.getBestBlockHash();
        assertThat(blockHash).isNotEmpty();
        assertThat(blockHash).hasSize(64);
    }

    @Test
    void combinedBlockMetadataTest() {
        int blockCount = daemon.getBlockCount();
        String blockHash = daemon.getBlockHash(blockCount);
        String bestBlockHash = daemon.getBestBlockHash();

        assertThat(blockHash).isEqualTo(bestBlockHash);
    }
}
