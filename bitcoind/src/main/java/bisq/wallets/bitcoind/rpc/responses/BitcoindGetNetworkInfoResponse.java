package bisq.wallets.bitcoind.rpc.responses;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;



import bisq.wallets.json_rpc.JsonRpcResponse;
import com.squareup.moshi.Json;

public class BitcoindGetNetworkInfoResponse extends JsonRpcResponse<BitcoindGetNetworkInfoResponse.Result> {
    @Getter
    public static class Result {
        private int version;
        private String subversion;
        @Json(name = "protocolversion")
        private int protocolVersion;

        @Json(name = "localservices")
        private String localServices;
        @Json(name = "localservicesnames")
        private List<String> localServicesNames;
        @Json(name = "localrelay")
        private boolean localRelay;
        @Json(name = "timeoffset")
        private int timeOffset;

        @Json(name = "networkactive")
        private boolean networkActive;
        private int connections;
        @Json(name = "connections_in")
        private int connectionsIn;
        @Json(name = "connections_out")
        private int connectionsOut;
        private List<Network> networks;

        @Json(name = "relayfee")
        private float relayFee;
        @Json(name = "incrementalfee")
        private float incrementalFee;

        @Json(name = "localaddresses")
        private List<LocalAddress> localAddresses;
        private List<String> warnings;
    }

    @Builder
    @EqualsAndHashCode
    @Getter
    public static class Network {
        private String name;
        private boolean limited;
        private boolean reachable;
        private String proxy;
        @Json(name = "proxy_randomize_credentials")
        private boolean proxyRandomizeCredentials;
    }

    @Getter
    public static class LocalAddress {
        private String address;
        private int port;
        private int score;
    }
}
