/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.wallets.bitcoind.rpc.responses;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        private Object warnings = Collections.emptyList();

        public List<String> getWarnings() {
            if (warnings == null) {
                return Collections.emptyList();
            }
            if (warnings instanceof String warning) {
                return warning.isEmpty() ? Collections.emptyList() : List.of(warning);
            }
            if (warnings instanceof List<?> warningList) {
                return warningList.stream()
                        .filter(Objects::nonNull)
                        .map(Object::toString)
                        .collect(Collectors.toUnmodifiableList());
            }
            return List.of(warnings.toString());
        }
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
