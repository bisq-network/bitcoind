package bisq.wallets.bitcoind.rpc.calls;

import bisq.wallets.json_rpc.DaemonRpcCall;
import bisq.wallets.json_rpc.reponses.JsonRpcStringResponse;

public class BitcoindGetBestBlockHashRpcCall extends DaemonRpcCall<Void, JsonRpcStringResponse> {

    public BitcoindGetBestBlockHashRpcCall() {
        super(null);
    }

    @Override
    public String getRpcMethodName() {
        return "getbestblockhash";
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
