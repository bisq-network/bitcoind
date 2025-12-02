package bisq.wallets.bitcoind.rpc.calls;

import bisq.wallets.json_rpc.DaemonRpcCall;
import bisq.wallets.json_rpc.reponses.JsonRpcIntResponse;

public class BitcoindGetBlockCountRpcCall extends DaemonRpcCall<Void, JsonRpcIntResponse> {
    public BitcoindGetBlockCountRpcCall() {
        super(null);
    }

    @Override
    public String getRpcMethodName() {
        return "getblockcount";
    }

    @Override
    public boolean isResponseValid(JsonRpcIntResponse response) {
        return true;
    }

    @Override
    public Class<JsonRpcIntResponse> getRpcResponseClass() {
        return JsonRpcIntResponse.class;
    }
}
