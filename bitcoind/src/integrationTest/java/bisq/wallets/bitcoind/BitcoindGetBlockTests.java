package bisq.wallets.bitcoind;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;



import bisq.wallets.bitcoind.rpc.BitcoindDaemon;
import bisq.wallets.bitcoind.rpc.BitcoindWallet;
import bisq.wallets.bitcoind.rpc.responses.BitcoindDecodeRawTransactionResponse;
import bisq.wallets.bitcoind.rpc.responses.BitcoindGetBlockResponse;
import bisq.wallets.bitcoind.rpc.responses.BitcoindScriptPubKey;
import bisq.wallets.bitcoind.rpc.responses.BitcoindTransaction;
import bisq.wallets.bitcoind.rpc.responses.BitcoindVin;
import bisq.wallets.core.model.AddressType;
import bisq.wallets.regtest.BitcoindExtension;
import bisq.wallets.regtest.bitcoind.BitcoindRegtestSetup;

@ExtendWith(BitcoindExtension.class)
public class BitcoindGetBlockTests {
    private final BitcoindRegtestSetup regtestSetup;
    private final BitcoindDaemon daemon;
    private final BitcoindWallet minerWallet;

    public BitcoindGetBlockTests(BitcoindRegtestSetup regtestSetup) {
        this.regtestSetup = regtestSetup;
        this.daemon = regtestSetup.getDaemon();
        this.minerWallet = regtestSetup.getMinerWallet();
    }

    @Test
    void blockVerbosityZeroTest() {
        String blockHash = daemon.getBestBlockHash();
        String serializedBlock = daemon.getBlockVerbosityZero(blockHash);
        assertThat(serializedBlock).isNotEmpty();
    }

    @Test
    void blockVerbosityOneTest() {
        var receiverBackend = regtestSetup.createAndInitializeNewWallet("receiver_wallet");
        String receiverAddress = receiverBackend.getNewAddress(AddressType.BECH32, "");
        String txId = minerWallet.sendToAddress(Optional.of(BitcoindRegtestSetup.WALLET_PASSPHRASE), receiverAddress, 1);
        regtestSetup.mineBlocks(2);

        int blockCount = daemon.getBlockCount();
        int blockHeight = blockCount - 1;
        String blockHash = daemon.getBlockHash(blockHeight);
        BitcoindGetBlockResponse.Result<String> block = daemon.getBlockVerbosityOne(blockHash);

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
        assertThat(block.getTxs()).contains(txId);
    }

    @Test
    void blockVerbosityTwoTest() {
        var receiverBackend = regtestSetup.createAndInitializeNewWallet("receiver_wallet");
        String receiverAddress = receiverBackend.getNewAddress(AddressType.BECH32, "");
        String txId = minerWallet.sendToAddress(Optional.of(BitcoindRegtestSetup.WALLET_PASSPHRASE), receiverAddress, 1);
        regtestSetup.mineBlocks(2);

        int blockCount = daemon.getBlockCount();
        int blockHeight = blockCount - 1;
        String blockHash = daemon.getBlockHash(blockHeight);
        BitcoindGetBlockResponse.Result<BitcoindTransaction> block = daemon.getBlockVerbosityTwo(blockHash);

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

        BitcoindDecodeRawTransactionResponse.Result transaction = block.getTxs().get(1);
        assertThat(transaction.getTxId()).isEqualTo(txId);
        assertThat(transaction.getHash()).hasSize(64);
        assertThat(transaction.getSize()).isGreaterThan(1);
        assertThat(transaction.getVsize()).isGreaterThan(1);
        assertThat(transaction.getWeight()).isGreaterThan(1);
        assertThat(transaction.getVersion()).isGreaterThanOrEqualTo(1);
        assertThat(transaction.getLockTime()).isGreaterThan(1);

        assertThat(transaction.getVin()).hasSizeGreaterThanOrEqualTo(1);
        BitcoindVin vin = transaction.getVin().get(0);
        assertThat(vin.getTxId()).hasSize(64);
        assertThat(vin.getTxInWitness()).hasSizeGreaterThanOrEqualTo(1);
        assertThat(vin.getSequence()).isGreaterThan(1);

        transaction.getVout().forEach(vout -> {
            assertThat(vout.getValue()).isGreaterThanOrEqualTo(1);

            BitcoindScriptPubKey scriptPubKey = vout.getScriptPubKey();
            assertThat(scriptPubKey.getAsm()).isNotEmpty();
            assertThat(scriptPubKey.getDesc()).startsWith("addr(");
            assertThat(scriptPubKey.getHex()).isNotEmpty();
            assertThat(scriptPubKey.getAddress()).startsWith("bcr");
            assertThat(scriptPubKey.getType()).isEqualTo("witness_v0_keyhash");
        });
    }
}
