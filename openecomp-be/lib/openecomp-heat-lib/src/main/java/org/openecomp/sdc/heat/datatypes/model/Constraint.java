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

package org.openecomp.sdc.heat.datatypes.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Constraint {

    private Object[] length;
    @Setter(AccessLevel.NONE)
    private Integer[] range;
    private List<Object> validValues;
    private String pattern;

    public Constraint() {
    }

    public void setRange(Integer[] inRange) {
        this.range = new Integer[] {inRange[0], inRange[1]};
    }

    /**
     * Add valid value.
     *
     * @param validValue the valid value
     */
    public void addValidValue(Object validValue) {
        if (this.validValues == null) {
            this.validValues = new ArrayList<>();
        }
        validValues.add(validValue);
    }
}
