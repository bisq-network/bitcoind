package bisq.wallets.bitcoind;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;



import bisq.wallets.bitcoind.rpc.BitcoindDaemon;
import bisq.wallets.bitcoind.rpc.responses.BitcoindGetBlockVerbosityOneResponse;
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

    @Test
    void blockVerbosityOneTest() {
        int blockCount = daemon.getBlockCount();
        int blockHeight = blockCount - 1;
        String blockHash = daemon.getBlockHash(blockHeight);
        BitcoindGetBlockVerbosityOneResponse.Result block = daemon.getBlockVerbosityOne(blockHash);

        assertThat(block.getHash()).isEqualTo(blockHash);
        assertThat(block.getConfirmations()).isEqualTo(2);
        assertThat(block.getHeight()).isEqualTo(blockHeight);
        assertThat(block.getVersion()).isGreaterThan(1);
        assertThat(block.getVersionHex()).isNotEmpty();
        assertThat(block.getMerkleRoot()).hasSize(64);

        assertThat(block.getTime()).isGreaterThan(1);
        assertThat(block.getMedianTime()).isGreaterThan(1);
        assertThat(block.getBits()).isNotEmpty();
        assertThat(block.getTarget()).isNotEmpty();
        assertThat(block.getDifficulty()).isGreaterThan(0);
        assertThat(block.getChainWork()).isNotEmpty();
        assertThat(block.getNTx()).isGreaterThanOrEqualTo(1);

        String previousBlockHash = daemon.getBlockHash(blockHeight - 1);
        assertThat(block.getPreviousBlockHash()).isEqualTo(previousBlockHash);

        String nextBlockHash = daemon.getBlockHash(blockHeight + 1);
        assertThat(block.getNextBlockHash()).isEqualTo(nextBlockHash);

        assertThat(block.getStrippedSize()).isGreaterThanOrEqualTo(1);
        assertThat(block.getSize()).isGreaterThanOrEqualTo(1);
        assertThat(block.getWeight()).isGreaterThanOrEqualTo(1);

        assertThat(block.getTxs()).hasSize(block.getNTx());
        assertThat(block.getTxs()).allMatch(txId -> txId.length() == 64);
    }
}
