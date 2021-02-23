/*
 * Copyright © 2016-2018 European Support Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openecomp.sdcrests.vendorlicense.rest.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openecomp.sdc.vendorlicense.dao.types.LimitEntity;
import org.junit.jupiter.api.Test;

class MapLimitEntityToLimitCreationDtoTest {

    @Test
    void testId() {
        LimitEntity source = new LimitEntity();
        LimitCreationDto target = new LimitCreationDto();
        MapLimitEntityToLimitCreationDto mapper = new MapLimitEntityToLimitCreationDto();
        String param = "52d4d919-015a-4a46-af04-4d0dec17e88d";
        source.setId(param);
        mapper.doMapping(source, target);
        assertEquals(target.getLimitId(), param);
    }

}
