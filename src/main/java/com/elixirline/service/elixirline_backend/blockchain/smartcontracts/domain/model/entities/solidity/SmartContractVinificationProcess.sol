// SPDX-License-Identifier: MIT
pragma solidity 0.8.19;

contract SmartContractVinificationProcess {
    struct Stage {
        uint256 stageId;
        string stageName;
        uint256 startDate;
        uint256 endDate;
        string dataHash;
        bool completed;
    }

    mapping(uint256 => mapping(string => Stage)) private stages;

    event StageSigned(
        uint256 indexed batchId,
        uint256 stageId,
        string indexed stageName,
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
        Stage storage s = stages[batchId][stageName];
        require(!s.completed, "Stage already signed");

        s.stageId = stageId;
        s.stageName = stageName;
        s.startDate = startDate;
        s.endDate = endDate;
        s.dataHash = dataHash;
        s.completed = true;

        emit StageSigned(batchId, stageId, stageName, startDate, endDate, dataHash);
    }

    function isStageSigned(uint256 batchId, string memory stageName) public view returns (bool) {
        return stages[batchId][stageName].completed;
    }

    function getStageDetails(uint256 batchId, string memory stageName) public view returns (
        uint256 stageId,
        string memory stageNameOut,
        uint256 startDate,
        uint256 endDate,
        string memory dataHash,
        bool completed
    )
    {
        Stage memory s = stages[batchId][stageName];
        require(bytes(s.stageName).length > 0, "Stage not found");

        return (s.stageId, s.stageName, s.startDate, s.endDate, s.dataHash, s.completed);
    }
}