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
package org.openecomp.sdc.vendorsoftwareproduct.dao.type;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openecomp.core.utilities.json.JsonUtil;
import org.openecomp.sdc.vendorsoftwareproduct.types.composition.CompositionEntityId;
import org.openecomp.sdc.vendorsoftwareproduct.types.composition.CompositionEntityType;
import org.openecomp.sdc.vendorsoftwareproduct.types.composition.Network;
import org.openecomp.sdc.versioning.dao.types.Version;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(keyspace = "dox", name = "vsp_network")
public class NetworkEntity implements CompositionEntity {

    private static final String ENTITY_TYPE = "Vendor Software Product Network";
    @PartitionKey
    @Column(name = "vsp_id")
    private String vspId;
    @PartitionKey(value = 1)
    @Frozen
    private Version version;
    @ClusteringColumn
    @Column(name = "network_id")
    private String id;
    @Column(name = "composition_data")
    private String compositionData;
    @Column(name = "questionnaire_data")
    private String questionnaireData;

    /**
     * Instantiates a new Network entity.
     *
     * @param vspId   the vsp id
     * @param version the version
     * @param id      the id
     */
    public NetworkEntity(String vspId, Version version, String id) {
        this.vspId = vspId;
        this.version = version;
        this.id = id;
    }

    @Override
    public CompositionEntityType getType() {
        return CompositionEntityType.network;
    }

    @Override
    public CompositionEntityId getCompositionEntityId() {
        return new CompositionEntityId(getId(), new CompositionEntityId(getVspId(), null));
    }

    @Override
    public String getEntityType() {
        return ENTITY_TYPE;
    }

    @Override
    public String getFirstClassCitizenId() {
        return getVspId();
    }

    public Network getNetworkCompositionData() {
        return compositionData == null ? null : JsonUtil.json2Object(compositionData, Network.class);
    }

    public void setNetworkCompositionData(Network network) {
        this.compositionData = network == null ? null : JsonUtil.object2Json(network);
    }
}
