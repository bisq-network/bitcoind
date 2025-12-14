package bisq.wallets.bitcoind.rpc.calls;

import lombok.Getter;



import bisq.wallets.json_rpc.DaemonRpcCall;
import bisq.wallets.json_rpc.reponses.JsonRpcStringResponse;
import com.squareup.moshi.Json;

public class BitcoindGetBlockVerbosityZeroRpcCall extends DaemonRpcCall<BitcoindGetBlockVerbosityZeroRpcCall.Request, JsonRpcStringResponse> {

    @Getter
    public static class Request {
        @Json(name = "blockhash")
        private String blockHash;
        private final int verbosity = 0;

        public Request(String blockHash) {
            this.blockHash = blockHash;
        }
    }

    public BitcoindGetBlockVerbosityZeroRpcCall(BitcoindGetBlockVerbosityZeroRpcCall.Request request) {
        super(request);
    }

    @Override
    public String getRpcMethodName() {
        return "getblock";
    }

    @Override
    public boolean isResponseValid(JsonRpcStringResponse response) {
        return response.getResult() != null;
    }

    @Override
    public Class<JsonRpcStringResponse> getRpcResponseClass() {
        return JsonRpcStringResponse.class;
    }
}
