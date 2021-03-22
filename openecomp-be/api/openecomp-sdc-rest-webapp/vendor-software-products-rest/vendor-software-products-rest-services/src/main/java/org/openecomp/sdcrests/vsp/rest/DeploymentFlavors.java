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
package org.openecomp.sdcrests.vsp.rest;

import static org.openecomp.sdcrests.common.RestConstants.USER_ID_HEADER_PARAM;
import static org.openecomp.sdcrests.common.RestConstants.USER_MISSING_ERROR_MSG;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.openecomp.sdcrests.vendorsoftwareproducts.types.DeploymentFlavorDto;
import org.openecomp.sdcrests.vendorsoftwareproducts.types.DeploymentFlavorListResponseDto;
import org.openecomp.sdcrests.vendorsoftwareproducts.types.DeploymentFlavorRequestDto;
import org.springframework.validation.annotation.Validated;

@Path("/v1.0/vendor-software-products/{vspId}/versions/{versionId}/deployment-flavors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tags({@Tag(name = "SDCE-1 APIs"), @Tag(name = "Vendor Software Product deployment-flavors")})
@Validated
public interface DeploymentFlavors extends VspEntities {

    @POST
    @Path("/")
    @Operation(description = "Create a vendor software product Deployment Flavor")
    Response create(@Valid DeploymentFlavorRequestDto request,
                    @Parameter(description = "Vendor software product Id") @PathParam("vspId") String vspId,
                    @Parameter(description = "Version Id") @PathParam("versionId") String versionId,
                    @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);

    @GET
    @Path("/")
    @Operation(description = "List vendor software product Deployment Flavor", responses = @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = DeploymentFlavorListResponseDto.class)))))
    Response list(@Parameter(description = "Vendor software product Id") @PathParam("vspId") String vspId,
                  @Parameter(description = "Version Id") @PathParam("versionId") String versionId,
                  @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);

    @GET
    @Path("/{deploymentFlavorId}")
    @Operation(description = "Get vendor software product Deployment Flavor", responses = @ApiResponse(content = @Content(schema = @Schema(implementation = DeploymentFlavorDto.class))))
    Response get(@Parameter(description = "Vendor software product Id") @PathParam("vspId") String vspId,
                 @Parameter(description = "Version Id") @PathParam("versionId") String versionId,
                 @Parameter(description = "Vendor software product Deployment Flavor Id") @PathParam("deploymentFlavorId") String deploymentFlavorId,
                 @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);

    @GET
    @Path("/schema")
    Response getSchema(@Parameter(description = "Vendor software product Id") @PathParam("vspId") String vspId,
                       @PathParam("versionId") String versionId,
                       @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);

    @DELETE
    @Path("/{deploymentFlavorId}")
    @Operation(description = "Delete vendor software product Deployment Flavor")
    Response delete(@Parameter(description = "Vendor software product Id") @PathParam("vspId") String vspId,
                    @Parameter(description = "Version Id") @PathParam("versionId") String versionId,
                    @Parameter(description = "Vendor software product Deployment Flavor Id") @PathParam("deploymentFlavorId") String deploymentFlavorId,
                    @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);

    @PUT
    @Path("/{deploymentFlavorId}")
    @Operation(description = "Update vendor software product Deployment Flavor")
    Response update(@Valid DeploymentFlavorRequestDto request,
                    @Parameter(description = "Vendor software product Id") @PathParam("vspId") String vspId,
                    @Parameter(description = "Version Id") @PathParam("versionId") String versionId,
                    @Parameter(description = "Vendor software product Deployment Flavor Id") @PathParam("deploymentFlavorId") String deploymentFlavorId,
                    @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);
}
