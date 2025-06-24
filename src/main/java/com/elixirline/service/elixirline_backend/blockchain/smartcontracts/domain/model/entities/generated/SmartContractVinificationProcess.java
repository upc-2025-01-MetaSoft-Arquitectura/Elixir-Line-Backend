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
import org.web3j.tuples.generated.Tuple5;
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
    public static final String BINARY = "608060405234801561001057600080fd5b506106fe806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806316221149146100465780636b8c6513146100735780637cb272ca146100b2575b600080fd5b61005961005436600461035b565b6100c7565b60405161006a9594939291906103c3565b60405180910390f35b6100a261008136600461035b565b60009182526020828152604080842092845291905290206004015460ff1690565b604051901515815260200161006a565b6100c56100c03660046104ac565b610271565b005b6060600080606060008060008089815260200190815260200160002060008881526020019081526020016000206040518060a001604052908160008201805461010f90610535565b80601f016020809104026020016040519081016040528092919081815260200182805461013b90610535565b80156101885780601f1061015d57610100808354040283529160200191610188565b820191906000526020600020905b81548152906001019060200180831161016b57829003601f168201915b5050505050815260200160018201548152602001600282015481526020016003820180546101b590610535565b80601f01602080910402602001604051908101604052809291908181526020018280546101e190610535565b801561022e5780601f106102035761010080835404028352916020019161022e565b820191906000526020600020905b81548152906001019060200180831161021157829003601f168201915b50505091835250506004919091015460ff16151560209182015281519082015160408301516060840151608090940151929c919b50995091975095509350505050565b6000868152602081815260408083208884529091529020600481015460ff16156102d85760405162461bcd60e51b815260206004820152601460248201527314dd1859d948185b1c9958591e481cda59db995960621b604482015260640160405180910390fd5b806102e386826105be565b5060018101849055600281018390556003810161030083826105be565b5060048101805460ff191660011790556040517fee03db6d87c987de1485d2b0b79a55871115fa591e1796c62984e530aa3b30139061034a9089908990899089908990899061067e565b60405180910390a150505050505050565b6000806040838503121561036e57600080fd5b50508035926020909101359150565b6000815180845260005b818110156103a357602081850181015186830182015201610387565b506000602082860101526020601f19601f83011685010191505092915050565b60a0815260006103d660a083018861037d565b86602084015285604084015282810360608401526103f4818661037d565b91505082151560808301529695505050505050565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261043057600080fd5b813567ffffffffffffffff8082111561044b5761044b610409565b604051601f8301601f19908116603f0116810190828211818310171561047357610473610409565b8160405283815286602085880101111561048c57600080fd5b836020870160208301376000602085830101528094505050505092915050565b60008060008060008060c087890312156104c557600080fd5b8635955060208701359450604087013567ffffffffffffffff808211156104eb57600080fd5b6104f78a838b0161041f565b9550606089013594506080890135935060a089013591508082111561051b57600080fd5b5061052889828a0161041f565b9150509295509295509295565b600181811c9082168061054957607f821691505b60208210810361056957634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156105b957600081815260208120601f850160051c810160208610156105965750805b601f850160051c820191505b818110156105b5578281556001016105a2565b5050505b505050565b815167ffffffffffffffff8111156105d8576105d8610409565b6105ec816105e68454610535565b8461056f565b602080601f83116001811461062157600084156106095750858301515b600019600386901b1c1916600185901b1785556105b5565b600085815260208120601f198616915b8281101561065057888601518255948401946001909101908401610631565b508582101561066e5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b86815285602082015260c06040820152600061069d60c083018761037d565b85606084015284608084015282810360a08401526106bb818561037d565b999850505050505050505056fea2646970667358221220ff82251eecfcf2fa8f8bca752e9e41a982a0520ad872793b27233ef1b74cf86e64736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETSTAGEDETAILS = "getStageDetails";

    public static final String FUNC_ISSTAGESIGNED = "isStageSigned";

    public static final String FUNC_SIGNSTAGE = "signStage";

    public static final Event STAGESIGNED_EVENT = new Event("StageSigned", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
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
            typedResponse.batchId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.stageId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.stageName = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.dataHash = (String) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static StageSignedEventResponse getStageSignedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STAGESIGNED_EVENT, log);
        StageSignedEventResponse typedResponse = new StageSignedEventResponse();
        typedResponse.log = log;
        typedResponse.batchId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.stageId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.stageName = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.startDate = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.endDate = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.dataHash = (String) eventValues.getNonIndexedValues().get(5).getValue();
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

    public RemoteFunctionCall<Tuple5<String, BigInteger, BigInteger, String, Boolean>> getStageDetails(
            BigInteger batchId, BigInteger stageId) {
        final Function function = new Function(FUNC_GETSTAGEDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(batchId), 
                new org.web3j.abi.datatypes.generated.Uint256(stageId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple5<String, BigInteger, BigInteger, String, Boolean>>(function,
                new Callable<Tuple5<String, BigInteger, BigInteger, String, Boolean>>() {
                    @Override
                    public Tuple5<String, BigInteger, BigInteger, String, Boolean> call() throws
                            Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, BigInteger, BigInteger, String, Boolean>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> isStageSigned(BigInteger batchId, BigInteger stageId) {
        final Function function = new Function(FUNC_ISSTAGESIGNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(batchId), 
                new org.web3j.abi.datatypes.generated.Uint256(stageId)), 
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

        public BigInteger stageId;

        public String stageName;

        public BigInteger startDate;

        public BigInteger endDate;

        public String dataHash;
    }
}
