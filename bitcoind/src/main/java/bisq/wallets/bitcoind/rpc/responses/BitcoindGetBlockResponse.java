package bisq.wallets.bitcoind.rpc.responses;

import java.util.List;

import lombok.Getter;



import bisq.wallets.json_rpc.JsonRpcResponse;
import com.squareup.moshi.Json;

public class BitcoindGetBlockResponse<T> extends JsonRpcResponse<BitcoindGetBlockResponse.Result<T>> {
    @Getter
    public static class Result<T> {
        private String hash;
        private int confirmations;
        private int height;
        private int version;
        private String versionHex;

        @Json(name = "merkleroot")
        private String merkleRoot;
        private long time;
        @Json(name = "mediantime")
        private long medianTime;
        private int nonce;
        private String bits;

        private String target;
        private float difficulty;
        @Json(name = "chainwork")
        private String chainWork;
        private int nTx;
        @Json(name = "previousblockhash")
        private String previousBlockHash;
        @Json(name = "nextblockhash")
        private String nextBlockHash;

        @Json(name = "strippedsize")
        private int strippedSize;
        private int size;
        private int weight;

        @Json(name = "tx")
        private List<T> txs;
    }

    public static class VerbosityOne extends BitcoindGetBlockResponse<String> {
    }

    public static class VerbosityTwo extends BitcoindGetBlockResponse<BitcoindTransaction> {
    }
}
