// SPDX-License-Identifier: MIT
pragma solidity 0.8.19;

contract SmartContractVinificationProcess {
    struct Stage {
        string stageName;
        uint256 startDate;
        uint256 endDate;
        string dataHash;
        bool completed;
    }

    mapping(uint256 => mapping(uint256 => Stage)) private stages;

    event StageSigned(
        uint256 batchId,
        uint256 stageId,
        string stageName,
        uint256 startDate,
        uint256 endDate,
        string dataHash
    );

    function signStage(
        uint256 batchId,
        uint256 stageId,
        string memory stageName,
        uint256 startDate,
        uint256 endDate,
        string memory dataHash
    ) public {
        Stage storage s = stages[batchId][stageId];
        require(!s.completed, "Stage already signed");

        s.stageName = stageName;
        s.startDate = startDate;
        s.endDate = endDate;
        s.dataHash = dataHash;
        s.completed = true;

        emit StageSigned(batchId, stageId, stageName, startDate, endDate, dataHash);
    }

    function isStageSigned(uint256 batchId, uint256 stageId) public view returns (bool) {
        return stages[batchId][stageId].completed;
    }

    function getStageDetails(uint256 batchId, uint256 stageId) public view returns (
        string memory stageName,
        uint256 startDate,
        uint256 endDate,
        string memory dataHash,
        bool completed
    ) {
        Stage memory s = stages[batchId][stageId];
        return (s.stageName, s.startDate, s.endDate, s.dataHash, s.completed);
    }
}
