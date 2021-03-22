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
package org.openecomp.sdcrests.vendorlicense.rest;

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
import org.openecomp.sdcrests.vendorlicense.types.LicenseKeyGroupEntityDto;
import org.openecomp.sdcrests.vendorlicense.types.LicenseKeyGroupRequestDto;
import org.springframework.validation.annotation.Validated;

@Path("/v1.0/vendor-license-models/{vlmId}/versions/{versionId}/license-key-groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tags({@Tag(name = "SDCE-1 APIs"), @Tag(name = "Vendor License Model - License Key Groups")})
@Validated
public interface LicenseKeyGroups {

    @GET
    @Path("/")
    @Operation(description = "List vendor license key groups", responses = @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = LicenseKeyGroupEntityDto.class)))))
    Response listLicenseKeyGroups(@Parameter(description = "Vendor license model Id") @PathParam("vlmId") String vlmId,
                                  @Parameter(description = "Vendor license model version Id") @PathParam("versionId") String versionId,
                                  @HeaderParam(USER_ID_HEADER_PARAM) String user);

    @POST
    @Path("/")
    @Operation(description = "Create vendor license key group")
    Response createLicenseKeyGroup(@Valid LicenseKeyGroupRequestDto request,
                                   @Parameter(description = "Vendor license model Id") @PathParam("vlmId") String vlmId,
                                   @Parameter(description = "Vendor license model version Id") @PathParam("versionId") String versionId,
                                   @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);

    @PUT
    @Path("/{licenseKeyGroupId}")
    @Operation(description = "Update vendor license key group")
    Response updateLicenseKeyGroup(@Valid LicenseKeyGroupRequestDto request,
                                   @Parameter(description = "Vendor license model Id") @PathParam("vlmId") String vlmId,
                                   @Parameter(description = "Vendor license model version Id") @PathParam("versionId") String versionId,
                                   @PathParam("licenseKeyGroupId") String licenseKeyGroupId,
                                   @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);

    @GET
    @Path("/{licenseKeyGroupId}")
    @Operation(description = "Get vendor license key group", responses = @ApiResponse(content = @Content(schema = @Schema(implementation = LicenseKeyGroupEntityDto.class))))
    Response getLicenseKeyGroup(@Parameter(description = "Vendor license model Id") @PathParam("vlmId") String vlmId,
                                @Parameter(description = "Vendor license model version Id") @PathParam("versionId") String versionId,
                                @PathParam("licenseKeyGroupId") String licenseKeyGroupId,
                                @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);

    @DELETE
    @Path("/{licenseKeyGroupId}")
    @Operation(description = "Delete vendor license key group")
    Response deleteLicenseKeyGroup(@Parameter(description = "Vendor license model Id") @PathParam("vlmId") String vlmId,
                                   @Parameter(description = "Vendor license model version Id") @PathParam("versionId") String versionId,
                                   @PathParam("licenseKeyGroupId") String licenseKeyGroupId,
                                   @NotNull(message = USER_MISSING_ERROR_MSG) @HeaderParam(USER_ID_HEADER_PARAM) String user);
}
