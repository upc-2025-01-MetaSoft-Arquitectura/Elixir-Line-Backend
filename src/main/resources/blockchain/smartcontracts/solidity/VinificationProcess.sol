// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract VinificationProcess {
    struct Stage {
        bool completed;
        uint256 completedAt;
        string dataHash;
        address signedBy;
    }

    // batchId => stageName => Stage
    mapping(string => mapping(string => Stage)) public stages;

    event StageSigned(string batchId, string stageName, address signer, uint256 completedAt, string dataHash);

    function signStage(
        string memory batchId,
        string memory stageName,
        string memory dataHash
    ) public {
        Stage storage s = stages[batchId][stageName];
        require(!s.completed, "Stage already signed");
        s.completed = true;
        s.completedAt = block.timestamp;
        s.dataHash = dataHash;
        s.signedBy = msg.sender;

        emit StageSigned(batchId, stageName, msg.sender, block.timestamp, dataHash);
    }

    function isStageSigned(string memory batchId, string memory stageName) public view returns (bool) {
        return stages[batchId][stageName].completed;
    }
}
