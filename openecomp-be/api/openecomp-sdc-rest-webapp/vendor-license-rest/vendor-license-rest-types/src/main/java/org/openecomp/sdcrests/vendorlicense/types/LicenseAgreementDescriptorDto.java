/*-
 * ============LICENSE_START=======================================================
 * SDC
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
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
package org.openecomp.sdcrests.vendorlicense.types;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.openecomp.sdc.vendorlicense.dao.types.LicenseTerm;

public class LicenseAgreementDescriptorDto {

    @NotNull
    @Size(max = 120)
    private String name;
    @Size(max = 1000)
    private String description;
    @NotNull
    @Valid
    private ChoiceOrOtherDto<LicenseTerm> licenseTerm;
    @Size(max = 1000)
    private String requirementsAndConstrains;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChoiceOrOtherDto<LicenseTerm> getLicenseTerm() {
        return licenseTerm;
    }

    public void setLicenseTerm(ChoiceOrOtherDto<LicenseTerm> licenseTerm) {
        this.licenseTerm = licenseTerm;
    }

    public String getRequirementsAndConstrains() {
        return requirementsAndConstrains;
    }

    public void setRequirementsAndConstrains(String requirementsAndConstrains) {
        this.requirementsAndConstrains = requirementsAndConstrains;
    }
}
