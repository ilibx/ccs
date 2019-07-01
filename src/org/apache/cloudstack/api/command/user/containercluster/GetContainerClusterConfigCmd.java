// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.cloudstack.api.command.user.containercluster;

import javax.inject.Inject;

import org.apache.cloudstack.acl.RoleType;
import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.BaseCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.ResponseObject;
import org.apache.cloudstack.api.response.ContainerClusterConfigResponse;
import org.apache.cloudstack.api.response.ContainerClusterResponse;
import org.apache.cloudstack.context.CallContext;
import org.apache.log4j.Logger;

import com.cloud.containercluster.ContainerClusterService;
import com.cloud.user.Account;


@APICommand(name = "getContainerClusterConfig",
        description = "Get container cluster config",
        responseObject = ContainerClusterConfigResponse.class,
        responseView = ResponseObject.ResponseView.Restricted,
        requestHasSensitiveInfo = false,
        responseHasSensitiveInfo = true,
        authorized = {RoleType.Admin, RoleType.ResourceAdmin, RoleType.DomainAdmin, RoleType.User})
public class GetContainerClusterConfigCmd extends BaseCmd {

    @Inject
    public ContainerClusterService _containerClusterService;

    public static final Logger s_logger = Logger.getLogger(GetContainerClusterConfigCmd.class.getName());

    private static final String s_name = "getcontainerclusterconfigresponse";

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////
    @Parameter(name = ApiConstants.ID, type = CommandType.UUID,
            entityType = ContainerClusterResponse.class,
            description = "the ID of the container cluster")
    private Long id;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////

    @Override
    public long getEntityOwnerId() {
        Account account = CallContext.current().getCallingAccount();
        if (account != null) {
            return account.getId();
        }

        return Account.ACCOUNT_ID_SYSTEM; // no account info given, parent this command to SYSTEM so ERROR events are tracked
    }

    @Override
    public String getCommandName() {
        return s_name;
    }

    @Override
    public void execute() {

        ContainerClusterConfigResponse response = _containerClusterService.getContainerClusterConfig(this);
        response.setResponseName(getCommandName());
        setResponseObject(response);
    }
}