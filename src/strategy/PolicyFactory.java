package strategy;

import utils.Config;

public class PolicyFactory {

    public static policyIF getPolicy(Config.POLICY policy){
        if(policy == Config.POLICY.POLICY_ITERATION){
            return new PolicyIteration();
        }else if(policy == Config.POLICY.VALUE_ITERATION){
            //return new ValueIteration();
        }
        throw new RuntimeException();
    }
}
