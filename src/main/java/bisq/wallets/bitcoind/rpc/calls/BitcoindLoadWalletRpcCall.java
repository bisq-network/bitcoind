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

package bisq.wallets.bitcoind.rpc.calls;

import bisq.wallets.bitcoind.rpc.responses.BitcoindCreateOrLoadWalletResponse;
import bisq.wallets.core.rpc.call.DaemonRpcCall;

public class BitcoindLoadWalletRpcCall
        extends DaemonRpcCall<BitcoindLoadWalletRpcCall.Request, BitcoindCreateOrLoadWalletResponse> {

    public record Request(String filename) {
    }

    public BitcoindLoadWalletRpcCall(Request request) {
        super(request);
    }

    @Override
    public String getRpcMethodName() {
        return "loadwallet";
    }

    @Override
    public boolean isResponseValid(BitcoindCreateOrLoadWalletResponse response) {
        return response.getName().equals(request.filename) && !response.hasWarning();
    }

    @Override
    public Class<BitcoindCreateOrLoadWalletResponse> getRpcResponseClass() {
        return BitcoindCreateOrLoadWalletResponse.class;
    }
}
