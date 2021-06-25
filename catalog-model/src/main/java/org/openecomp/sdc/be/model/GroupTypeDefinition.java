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
package org.openecomp.sdc.be.model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.openecomp.sdc.be.datatypes.elements.GroupTypeDataDefinition;

/**
 * Specifies the group type that the Node Type exposes.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class GroupTypeDefinition extends GroupTypeDataDefinition {

    private List<PropertyDefinition> properties;
    private Map<String, CapabilityDefinition> capabilities;
    private String model;

    public GroupTypeDefinition(GroupTypeDataDefinition p) {
        super(p);
    }

}
