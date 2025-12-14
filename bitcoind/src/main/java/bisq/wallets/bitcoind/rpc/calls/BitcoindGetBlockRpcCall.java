package bisq.wallets.bitcoind.rpc.calls;

import lombok.Getter;



import bisq.wallets.json_rpc.DaemonRpcCall;
import bisq.wallets.json_rpc.JsonRpcResponse;
import com.squareup.moshi.Json;

public class BitcoindGetBlockRpcCall<T extends JsonRpcResponse<?>> extends DaemonRpcCall<BitcoindGetBlockRpcCall.Request, T> {

    @Getter
    public static class Request {
        @Json(name = "blockhash")
        private String blockHash;
        private int verbosity;

        public Request(String blockHash, int verbosity) {
            this.blockHash = blockHash;
            this.verbosity = verbosity;
        }
    }

    private final Class<T> responseClass;

    public BitcoindGetBlockRpcCall(BitcoindGetBlockRpcCall.Request request, Class<T> responseClass) {
        super(request);
        this.responseClass = responseClass;
    }

    @Override
    public String getRpcMethodName() {
        return "getblock";
    }

    @Override
    public boolean isResponseValid(T response) {
        return response.getResult() != null;
    }

    @Override
    public Class<T> getRpcResponseClass() {
        return responseClass;
    }
}
