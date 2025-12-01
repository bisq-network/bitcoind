package bisq.wallets.bitcoind.rpc.calls;

import bisq.wallets.bitcoind.rpc.responses.BitcoindGetNetworkInfoResponse;
import bisq.wallets.json_rpc.DaemonRpcCall;

public class BitcoindGetNetworkInfoRpcCall extends DaemonRpcCall<Void, BitcoindGetNetworkInfoResponse> {
    public BitcoindGetNetworkInfoRpcCall() {
        super(null);
    }

    @Override
    public String getRpcMethodName() {
        return "getnetworkinfo";
    }

    @Override
    public boolean isResponseValid(BitcoindGetNetworkInfoResponse response) {
        return true;
    }

    @Override
    public Class<BitcoindGetNetworkInfoResponse> getRpcResponseClass() {
        return BitcoindGetNetworkInfoResponse.class;
    }
}
