/*-
 * ============LICENSE_START=======================================================
 * SDC
 * ================================================================================
 * Copyright (C) 2019 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */
package org.openecomp.sdc.be.components.impl;

import static java.util.Collections.emptySet;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.openecomp.sdc.be.components.impl.exceptions.ByActionStatusComponentException;
import org.openecomp.sdc.be.components.validation.UserValidations;
import org.openecomp.sdc.be.config.ConfigurationManager;
import org.openecomp.sdc.be.dao.api.ActionStatus;
import org.openecomp.sdc.be.dao.jsongraph.JanusGraphDao;
import org.openecomp.sdc.be.impl.ComponentsUtils;
import org.openecomp.sdc.be.model.PolicyTypeDefinition;
import org.openecomp.sdc.be.model.operations.api.StorageOperationStatus;
import org.openecomp.sdc.be.model.operations.impl.PolicyTypeOperation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PolicyTypeBusinessLogic {

    private PolicyTypeOperation policyTypeOperation;
    private JanusGraphDao janusGraphDao;
    private ComponentsUtils componentsUtils;
    private UserValidations userValidations;

    public PolicyTypeBusinessLogic(PolicyTypeOperation policyTypeOperation, JanusGraphDao janusGraphDao, ComponentsUtils componentsUtils,
                                   UserValidations userValidations) {
        this.policyTypeOperation = policyTypeOperation;
        this.janusGraphDao = janusGraphDao;
        this.componentsUtils = componentsUtils;
        this.userValidations = userValidations;
    }

    @Transactional
    public List<PolicyTypeDefinition> getAllPolicyTypes(String userId, String internalComponentType, String modelName) {
        Set<String> excludedPolicyTypes = getExcludedPolicyTypes(internalComponentType);
        userValidations.validateUserExists(userId);
        return getPolicyTypes(excludedPolicyTypes, modelName);
    }

    public PolicyTypeDefinition getLatestPolicyTypeByType(String policyTypeName, String modelName) {
        return policyTypeOperation.getLatestPolicyTypeByType(policyTypeName, modelName).left().on(e -> failOnPolicyType(e, policyTypeName));
    }

    public Set<String> getExcludedPolicyTypes(String internalComponentType) {
        if (StringUtils.isEmpty(internalComponentType)) {
            return emptySet();
        }
        Map<String, Set<String>> excludedPolicyTypesMapping = ConfigurationManager.getConfigurationManager().getConfiguration()
            .getExcludedPolicyTypesMapping();
        Set<String> excludedTypes = excludedPolicyTypesMapping.get(internalComponentType);
        return excludedTypes == null ? emptySet() : excludedTypes;
    }

    private List<PolicyTypeDefinition> getPolicyTypes(Set<String> excludedTypes, String modelName) {
        return policyTypeOperation.getAllPolicyTypes(excludedTypes, modelName);
    }

    private PolicyTypeDefinition failOnPolicyType(StorageOperationStatus status, String policyType) {
        janusGraphDao.rollback();
        if (status == StorageOperationStatus.INVALID_ID) {
            throw new ByActionStatusComponentException(ActionStatus.POLICY_TYPE_IS_INVALID, policyType);
        } else {
            throw new ByActionStatusComponentException(ActionStatus.GENERAL_ERROR);
        }
    }
}
