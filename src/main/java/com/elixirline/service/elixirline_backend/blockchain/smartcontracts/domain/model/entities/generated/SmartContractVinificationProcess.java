package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.entities.generated;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.13.0.
 */
@SuppressWarnings("rawtypes")
public class SmartContractVinificationProcess extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506107e8806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80637cb272ca146100465780639b15a4681461005b578063c99745cc14610089575b600080fd5b6100596100543660046104b1565b6100ac565b005b61006e61006936600461053a565b6101c6565b604051610080969594939291906105d1565b60405180910390f35b61009c61009736600461053a565b6103d4565b6040519015158152602001610080565b60008681526020819052604080822090516100c890879061061e565b908152604051908190036020019020600581015490915060ff161561012b5760405162461bcd60e51b815260206004820152601460248201527314dd1859d948185b1c9958591e481cda59db995960621b60448201526064015b60405180910390fd5b8581556001810161013c86826106c3565b5060028101849055600381018390556004810161015983826106c3565b5060058101805460ff1916600117905560405161017790869061061e565b6040518091039020877fee03db6d87c987de1485d2b0b79a55871115fa591e1796c62984e530aa3b3013888787876040516101b59493929190610783565b60405180910390a350505050505050565b6000606060008060606000806000808a8152602001908152602001600020886040516101f2919061061e565b90815260200160405180910390206040518060c0016040529081600082015481526020016001820180546102259061063a565b80601f01602080910402602001604051908101604052809291908181526020018280546102519061063a565b801561029e5780601f106102735761010080835404028352916020019161029e565b820191906000526020600020905b81548152906001019060200180831161028157829003601f168201915b5050505050815260200160028201548152602001600382015481526020016004820180546102cb9061063a565b80601f01602080910402602001604051908101604052809291908181526020018280546102f79061063a565b80156103445780601f1061031957610100808354040283529160200191610344565b820191906000526020600020905b81548152906001019060200180831161032757829003601f168201915b50505091835250506005919091015460ff161515602091820152810151519091506103a35760405162461bcd60e51b815260206004820152600f60248201526e14dd1859d9481b9bdd08199bdd5b99608a1b6044820152606401610122565b8051602082015160408301516060840151608085015160a090950151939d929c50909a509850919650945092505050565b60008281526020819052604080822090516103f090849061061e565b9081526040519081900360200190206005015460ff16905092915050565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261043557600080fd5b813567ffffffffffffffff808211156104505761045061040e565b604051601f8301601f19908116603f011681019082821181831017156104785761047861040e565b8160405283815286602085880101111561049157600080fd5b836020870160208301376000602085830101528094505050505092915050565b60008060008060008060c087890312156104ca57600080fd5b8635955060208701359450604087013567ffffffffffffffff808211156104f057600080fd5b6104fc8a838b01610424565b9550606089013594506080890135935060a089013591508082111561052057600080fd5b5061052d89828a01610424565b9150509295509295509295565b6000806040838503121561054d57600080fd5b82359150602083013567ffffffffffffffff81111561056b57600080fd5b61057785828601610424565b9150509250929050565b60005b8381101561059c578181015183820152602001610584565b50506000910152565b600081518084526105bd816020860160208601610581565b601f01601f19169290920160200192915050565b86815260c0602082015260006105ea60c08301886105a5565b866040840152856060840152828103608084015261060881866105a5565b91505082151560a0830152979650505050505050565b60008251610630818460208701610581565b9190910192915050565b600181811c9082168061064e57607f821691505b60208210810361066e57634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156106be57600081815260208120601f850160051c8101602086101561069b5750805b601f850160051c820191505b818110156106ba578281556001016106a7565b5050505b505050565b815167ffffffffffffffff8111156106dd576106dd61040e565b6106f1816106eb845461063a565b84610674565b602080601f831160018114610726576000841561070e5750858301515b600019600386901b1c1916600185901b1785556106ba565b600085815260208120601f198616915b8281101561075557888601518255948401946001909101908401610736565b50858210156107735787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b8481528360208201528260408201526080606082015260006107a860808301846105a5565b969550505050505056fea2646970667358221220fc26f7d33225a8703f34114fef6a4e5b51de1f4af335d5de9ca1b4fc1af69a7464736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETSTAGEDETAILS = "getStageDetails";

    public static final String FUNC_ISSTAGESIGNED = "isStageSigned";

    public static final String FUNC_SIGNSTAGE = "signStage";

    public static final Event STAGESIGNED_EVENT = new Event("StageSigned", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected SmartContractVinificationProcess(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SmartContractVinificationProcess(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SmartContractVinificationProcess(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SmartContractVinificationProcess(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<StageSignedEventResponse> getStageSignedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STAGESIGNED_EVENT, transactionReceipt);
        ArrayList<StageSignedEventResponse> responses = new ArrayList<StageSignedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StageSignedEventResponse typedResponse = new StageSignedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.batchId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.stageName = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.stageId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.dataHash = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static StageSignedEventResponse getStageSignedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STAGESIGNED_EVENT, log);
        StageSignedEventResponse typedResponse = new StageSignedEventResponse();
        typedResponse.log = log;
        typedResponse.batchId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.stageName = (byte[]) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.stageId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.dataHash = (String) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<StageSignedEventResponse> stageSignedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getStageSignedEventFromLog(log));
    }

    public Flowable<StageSignedEventResponse> stageSignedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STAGESIGNED_EVENT));
        return stageSignedEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, BigInteger, String, Boolean>> getStageDetails(
            BigInteger batchId, String stageName) {
        final Function function = new Function(FUNC_GETSTAGEDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(batchId), 
                new org.web3j.abi.datatypes.Utf8String(stageName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, BigInteger, String, Boolean>>(function,
                new Callable<Tuple6<BigInteger, String, BigInteger, BigInteger, String, Boolean>>() {
                    @Override
                    public Tuple6<BigInteger, String, BigInteger, BigInteger, String, Boolean> call(
                            ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, String, BigInteger, BigInteger, String, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> isStageSigned(BigInteger batchId, String stageName) {
        final Function function = new Function(FUNC_ISSTAGESIGNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(batchId), 
                new org.web3j.abi.datatypes.Utf8String(stageName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> signStage(BigInteger batchId, BigInteger stageId,
            String stageName, BigInteger startDate, BigInteger endDate, String dataHash) {
        final Function function = new Function(
                FUNC_SIGNSTAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(batchId), 
                new org.web3j.abi.datatypes.generated.Uint256(stageId), 
                new org.web3j.abi.datatypes.Utf8String(stageName), 
                new org.web3j.abi.datatypes.generated.Uint256(startDate), 
                new org.web3j.abi.datatypes.generated.Uint256(endDate), 
                new org.web3j.abi.datatypes.Utf8String(dataHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SmartContractVinificationProcess load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContractVinificationProcess(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SmartContractVinificationProcess load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContractVinificationProcess(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SmartContractVinificationProcess load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SmartContractVinificationProcess(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SmartContractVinificationProcess load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SmartContractVinificationProcess(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SmartContractVinificationProcess> deploy(Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SmartContractVinificationProcess.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SmartContractVinificationProcess> deploy(Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SmartContractVinificationProcess.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<SmartContractVinificationProcess> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SmartContractVinificationProcess.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SmartContractVinificationProcess> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SmartContractVinificationProcess.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class StageSignedEventResponse extends BaseEventResponse {
        public BigInteger batchId;

        public byte[] stageName;

        public BigInteger stageId;

        public BigInteger startDate;

        public BigInteger endDate;

        public String dataHash;
    }
}
