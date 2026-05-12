# Security Policy

  ## Supported Versions

  This repository provides Bisq-maintained Java wrappers, RPC helpers, regtest
  tooling, and build logic for using Bitcoin Core/`bitcoind`.

  Security fixes are applied to the active development branch and any active
  maintenance branches.

  | Version / Branch | Supported |
  | --- | --- |
  | `main` | :white_check_mark: |
  | Active maintenance or migration branches, such as `update_to_java21` while open | :x: |
  | Released artifacts built from the current supported branch | :x: |
  | Old commits, unsupported forks, or locally modified builds | :x: |

  Vulnerabilities in Bitcoin Core itself should also be reported to the Bitcoin
  Core project according to its own security policy. This policy covers the
  Bisq-maintained code in this repository, including RPC wrappers, binary download
  and verification logic, build tooling, and regtest support.

  ## Reporting a Vulnerability

  Please do **not** report security vulnerabilities through public GitHub issues,
  pull requests, Matrix rooms, forums, or social media.

  Report suspected vulnerabilities privately through GitHub's **Report a
  vulnerability** flow on this repository's Security page. If that option is not
  available, open a minimal public issue asking maintainers to enable a private
  security reporting channel, but do not include exploit details.

  Include as much detail as possible:

  - affected branch, commit, dependency, or build artifact;
  - affected component, such as `bitcoind`, `bitcoind-core`, `json-rpc`,
    `regtest`, or `build-logic`;
  - whether the issue affects mainnet, testnet/signet, or only regtest;
  - whether the issue can expose funds, private keys, wallet passphrases, RPC
    credentials, transaction data, or node connectivity;
  - reproduction steps, logs, stack traces, RPC requests/responses, or proof of
    concept code where useful;
  - whether the issue depends on a malicious RPC server, compromised binary
    download, invalid signature/hash verification, unsafe wallet operation,
    malformed ZMQ message, or untrusted local process.

  Bisq is an open-source project maintained by contributors. Response times may
  vary, but reports involving possible loss of funds, key or credential exposure,
  binary supply-chain compromise, transaction-signing mistakes, or unsafe wallet
  RPC behavior are treated as urgent security issues and will be triaged as
  quickly as possible.

  For lower-severity issues, maintainers will respond when contributor capacity is
  available.

  If the report is accepted, maintainers may coordinate a fix privately, prepare a
  patched release or dependency update, and publish an advisory after users have
  had a reasonable opportunity to update. If the report is declined, maintainers
  will explain the reason when possible.

  Please give maintainers reasonable time to investigate and release mitigations
  before public disclosure. For severe or actively exploited issues, coordinate
  timing with maintainers so public details do not increase risk to users.

  Bisq does not currently guarantee a bug bounty. Security work may be eligible
  for Bisq DAO compensation if it qualifies under the project's contributor and
  critical-bug processes.
