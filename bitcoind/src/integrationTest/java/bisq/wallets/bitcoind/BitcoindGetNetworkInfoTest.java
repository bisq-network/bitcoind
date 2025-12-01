package bisq.wallets.bitcoind;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;



import bisq.wallets.bitcoind.rpc.BitcoindDaemon;
import bisq.wallets.bitcoind.rpc.responses.BitcoindGetNetworkInfoResponse;
import bisq.wallets.regtest.BitcoindExtension;
import bisq.wallets.regtest.bitcoind.BitcoindRegtestSetup;

@ExtendWith(BitcoindExtension.class)
public class BitcoindGetNetworkInfoTest {
    private final BitcoindDaemon daemon;

    public BitcoindGetNetworkInfoTest(BitcoindRegtestSetup regtestSetup) {
        this.daemon = regtestSetup.getDaemon();
    }

    @Test
    void networkInfoTest() {
        BitcoindGetNetworkInfoResponse.Result networkInfo = daemon.getNetworkInfo().getResult();

        assertThat(networkInfo.getVersion()).isGreaterThanOrEqualTo(290200);
        assertThat(networkInfo.getSubversion()).startsWith("/Satoshi:");
        assertThat(networkInfo.getProtocolVersion()).isGreaterThanOrEqualTo(70016);
        assertThat(networkInfo.getLocalServices()).isNotEmpty()
                .isNotEqualTo("0000000000000000");

        List<String> expectedLocalServices = List.of("NETWORK", "BLOOM", "WITNESS", "NETWORK_LIMITED", "P2P_V2");
        assertThat(networkInfo.getLocalServicesNames()).containsAll(expectedLocalServices);
        assertThat(networkInfo.isLocalRelay()).isTrue();
        assertThat(networkInfo.getTimeOffset()).isEqualTo(0);

        assertThat(networkInfo.isNetworkActive()).isEqualTo(true);
        assertThat(networkInfo.getConnections()).isEqualTo(0);
        assertThat(networkInfo.getConnectionsIn()).isEqualTo(0);
        assertThat(networkInfo.getConnectionsOut()).isEqualTo(0);

        assertThat(networkInfo.getNetworks()).isNotEmpty();
        BitcoindGetNetworkInfoResponse.Network ipV4Network = BitcoindGetNetworkInfoResponse.Network.builder()
                .name("ipv4")
                .limited(false)
                .reachable(true)
                .proxy("")
                .proxyRandomizeCredentials(false)
                .build();
        BitcoindGetNetworkInfoResponse.Network ipV6Network = BitcoindGetNetworkInfoResponse.Network.builder()
                .name("ipv6")
                .limited(false)
                .reachable(true)
                .proxy("")
                .proxyRandomizeCredentials(false)
                .build();
        List<BitcoindGetNetworkInfoResponse.Network> expectedNetworks = List.of(ipV4Network, ipV6Network);
        assertThat(networkInfo.getNetworks()).containsAll(expectedNetworks);

        assertThat(networkInfo.getRelayFee()).isEqualTo(0.00000100f);
        assertThat(networkInfo.getIncrementalFee()).isEqualTo(0.00000100f);
        assertThat(networkInfo.getLocalAddresses()).isEmpty();
        assertThat(networkInfo.getWarnings()).isEmpty();
    }
}
