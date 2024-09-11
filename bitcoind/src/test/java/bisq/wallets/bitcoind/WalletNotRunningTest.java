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

package bisq.wallets.bitcoind;

import bisq.wallets.bitcoind.rpc.BitcoindDaemon;
import bisq.wallets.json_rpc.JsonRpcClient;
import bisq.wallets.json_rpc.RpcClientFactory;
import bisq.wallets.json_rpc.RpcConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WalletNotRunningTest {
    @Test
    void notRunningTest() {
        int freePort = findFreeSystemPort();

        RpcConfig rpcConfig = RpcConfig.builder()
                .hostname("127.0.0.1")
                .user("bisq")
                .password("bisq")
                .port(freePort)
                .build();

        JsonRpcClient rpcClient = RpcClientFactory.createDaemonRpcClient(rpcConfig);
        var minerChainBackend = new BitcoindDaemon(rpcClient);

        assertThatThrownBy(minerChainBackend::listWallets)
                .hasCauseInstanceOf(ConnectException.class);
    }

    public static int findFreeSystemPort() {
        try {
            ServerSocket server = new ServerSocket(0);
            int port = server.getLocalPort();
            server.close();
            return port;
        } catch (IOException ignored) {
            return new Random().nextInt(10000) + 50000;
        }
    }
}
