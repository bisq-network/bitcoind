package bisq.wallets.bitcoind.rpc.calls;

import lombok.Builder;
import lombok.Getter;



import bisq.wallets.json_rpc.DaemonRpcCall;
import bisq.wallets.json_rpc.reponses.JsonRpcStringResponse;

public class BitcoindGetBlockHashRpcCall extends DaemonRpcCall<BitcoindGetBlockHashRpcCall.Request, JsonRpcStringResponse> {

    @Builder
    @Getter
    public static class Request {
        private int height;

        public Request(int height) {
            this.height = height;
        }
    }

    public BitcoindGetBlockHashRpcCall(Request request) {
        super(request);
    }

    @Override
    public String getRpcMethodName() {
        return "getblockhash";
    }

    @Override
    public boolean isResponseValid(JsonRpcStringResponse response) {
        return true;
    }

    @Override
    public Class<JsonRpcStringResponse> getRpcResponseClass() {
        return JsonRpcStringResponse.class;
    }
}
