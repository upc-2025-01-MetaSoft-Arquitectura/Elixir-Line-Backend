package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.generated;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
import org.web3j.tuples.generated.Tuple4;
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
public class VinificationProcess extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b506106b68061001c5f395ff3fe608060405234801561000f575f5ffd5b506004361061003f575f3560e01c80636850084014610043578063738006e114610058578063d744cbf114610084575b5f5ffd5b610056610051366004610377565b6100a7565b005b61006b610066366004610405565b6101ae565b60405161007b9493929190610498565b60405180910390f35b610097610092366004610405565b610291565b604051901515815260200161007b565b5f5f846040516100b791906104d1565b9081526020016040518091039020836040516100d391906104d1565b908152604051908190036020019020805490915060ff16156101325760405162461bcd60e51b815260206004820152601460248201527314dd1859d948185b1c9958591e481cda59db995960621b604482015260640160405180910390fd5b805460ff191660019081178255429082015560028101610152838261056b565b506003810180546001600160a01b031916339081179091556040517fbd3ede6d74e6b7d77924f99713ad3060be9e1b594625934ef5b59cff2345e870916101a0918791879142908890610626565b60405180910390a150505050565b81516020818401810180515f82529282019482019490942091909352815180830184018051928152908401929093019190912091528054600182015460028301805460ff909316939192610201906104e7565b80601f016020809104026020016040519081016040528092919081815260200182805461022d906104e7565b80156102785780601f1061024f57610100808354040283529160200191610278565b820191905f5260205f20905b81548152906001019060200180831161025b57829003601f168201915b505050600390930154919250506001600160a01b031684565b5f5f836040516102a191906104d1565b9081526020016040518091039020826040516102bd91906104d1565b9081526040519081900360200190205460ff16905092915050565b634e487b7160e01b5f52604160045260245ffd5b5f82601f8301126102fb575f5ffd5b813567ffffffffffffffff811115610315576103156102d8565b604051601f8201601f19908116603f0116810167ffffffffffffffff81118282101715610344576103446102d8565b60405281815283820160200185101561035b575f5ffd5b816020850160208301375f918101602001919091529392505050565b5f5f5f60608486031215610389575f5ffd5b833567ffffffffffffffff81111561039f575f5ffd5b6103ab868287016102ec565b935050602084013567ffffffffffffffff8111156103c7575f5ffd5b6103d3868287016102ec565b925050604084013567ffffffffffffffff8111156103ef575f5ffd5b6103fb868287016102ec565b9150509250925092565b5f5f60408385031215610416575f5ffd5b823567ffffffffffffffff81111561042c575f5ffd5b610438858286016102ec565b925050602083013567ffffffffffffffff811115610454575f5ffd5b610460858286016102ec565b9150509250929050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b8415158152836020820152608060408201525f6104b8608083018561046a565b905060018060a01b038316606083015295945050505050565b5f82518060208501845e5f920191825250919050565b600181811c908216806104fb57607f821691505b60208210810361051957634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561056657805f5260205f20601f840160051c810160208510156105445750805b601f840160051c820191505b81811015610563575f8155600101610550565b50505b505050565b815167ffffffffffffffff811115610585576105856102d8565b6105998161059384546104e7565b8461051f565b6020601f8211600181146105cb575f83156105b45750848201515b5f19600385901b1c1916600184901b178455610563565b5f84815260208120601f198516915b828110156105fa57878501518255602094850194600190920191016105da565b508482101561061757868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b60a081525f61063860a083018861046a565b828103602084015261064a818861046a565b6001600160a01b03871660408501526060840186905283810360808501529050610674818561046a565b9897505050505050505056fea26469706673582212208046254473aaa768dae023c38b97545416c21dc2a8d11cdde1a221600a0071ec64736f6c634300081d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ISSTAGESIGNED = "isStageSigned";

    public static final String FUNC_SIGNSTAGE = "signStage";

    public static final String FUNC_STAGES = "stages";

    public static final Event STAGESIGNED_EVENT = new Event("StageSigned", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected VinificationProcess(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VinificationProcess(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VinificationProcess(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VinificationProcess(String contractAddress, Web3j web3j,
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
            typedResponse.batchId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.stageName = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.signer = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.completedAt = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.dataHash = (String) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static StageSignedEventResponse getStageSignedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STAGESIGNED_EVENT, log);
        StageSignedEventResponse typedResponse = new StageSignedEventResponse();
        typedResponse.log = log;
        typedResponse.batchId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.stageName = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.signer = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.completedAt = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.dataHash = (String) eventValues.getNonIndexedValues().get(4).getValue();
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

    public RemoteFunctionCall<Boolean> isStageSigned(String batchId, String stageName) {
        final Function function = new Function(FUNC_ISSTAGESIGNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(batchId), 
                new org.web3j.abi.datatypes.Utf8String(stageName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> signStage(String batchId, String stageName,
            String dataHash) {
        final Function function = new Function(
                FUNC_SIGNSTAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(batchId), 
                new org.web3j.abi.datatypes.Utf8String(stageName), 
                new org.web3j.abi.datatypes.Utf8String(dataHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<Boolean, BigInteger, String, String>> stages(String param0,
            String param1) {
        final Function function = new Function(FUNC_STAGES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0), 
                new org.web3j.abi.datatypes.Utf8String(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple4<Boolean, BigInteger, String, String>>(function,
                new Callable<Tuple4<Boolean, BigInteger, String, String>>() {
                    @Override
                    public Tuple4<Boolean, BigInteger, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<Boolean, BigInteger, String, String>(
                                (Boolean) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    @Deprecated
    public static VinificationProcess load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VinificationProcess(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VinificationProcess load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VinificationProcess(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VinificationProcess load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new VinificationProcess(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VinificationProcess load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VinificationProcess(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<VinificationProcess> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VinificationProcess.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<VinificationProcess> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VinificationProcess.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<VinificationProcess> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VinificationProcess.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<VinificationProcess> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VinificationProcess.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
        public String batchId;

        public String stageName;

        public String signer;

        public BigInteger completedAt;

        public String dataHash;
    }
}
