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

import com.squareup.moshi.Moshi;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class BitcoindGetNetworkInfoResponseTest {
    private static final Moshi MOSHI = new Moshi.Builder().build();

    @Test
    void parsesStringWarnings() throws IOException {
        BitcoindGetNetworkInfoResponse response = parse("""
                {"result":{"warnings":"This node has a warning"},"error":null,"id":"curltest"}
                """);

        assertThat(response.getResult().getWarnings()).containsExactly("This node has a warning");
    }

    @Test
    void parsesEmptyStringWarnings() throws IOException {
        BitcoindGetNetworkInfoResponse response = parse("""
                {"result":{"warnings":""},"error":null,"id":"curltest"}
                """);

        assertThat(response.getResult().getWarnings()).isEmpty();
    }

    @Test
    void parsesArrayWarnings() throws IOException {
        BitcoindGetNetworkInfoResponse response = parse("""
                {"result":{"warnings":["first warning","second warning"]},"error":null,"id":"curltest"}
                """);

        assertThat(response.getResult().getWarnings()).containsExactly("first warning", "second warning");
    }

    private BitcoindGetNetworkInfoResponse parse(String json) throws IOException {
        return MOSHI.adapter(BitcoindGetNetworkInfoResponse.class).fromJson(json);
    }
}
