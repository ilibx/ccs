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
package com.cloud.containercluster;

import org.apache.cloudstack.api.command.user.containercluster.CreateContainerClusterCmd;
import org.apache.cloudstack.api.command.user.containercluster.GetContainerClusterConfigCmd;
import org.apache.cloudstack.api.command.user.containercluster.ListContainerClusterCmd;
import org.apache.cloudstack.api.command.user.containercluster.ScaleContainerClusterCmd;
import org.apache.cloudstack.api.response.ContainerClusterConfigResponse;
import org.apache.cloudstack.api.response.ContainerClusterResponse;
import org.apache.cloudstack.api.response.ListResponse;

import com.cloud.exception.InsufficientCapacityException;
import com.cloud.exception.ManagementServerException;
import com.cloud.exception.ResourceAllocationException;
import com.cloud.exception.ResourceUnavailableException;
import com.cloud.utils.component.PluggableService;

public interface ContainerClusterService extends PluggableService {

    ContainerCluster findById(final Long id);

    ContainerCluster createContainerCluster(CreateContainerClusterCmd cmd) throws InsufficientCapacityException,
                     ResourceAllocationException, ManagementServerException;

    boolean startContainerCluster(long containerClusterId, boolean onCreate) throws ManagementServerException,
            ResourceAllocationException, ResourceUnavailableException, InsufficientCapacityException;

    boolean stopContainerCluster(long containerClusterId) throws ManagementServerException;

    boolean deleteContainerCluster(Long containerClusterId) throws ManagementServerException;

    ListResponse<ContainerClusterResponse> listContainerClusters(ListContainerClusterCmd cmd);

    ContainerClusterConfigResponse getContainerClusterConfig(GetContainerClusterConfigCmd cmd);

    ContainerClusterResponse createContainerClusterResponse(long containerClusterId);

    boolean scaleContainerCluster(ScaleContainerClusterCmd cmd) throws ManagementServerException,
            ResourceAllocationException, ResourceUnavailableException, InsufficientCapacityException;

}
