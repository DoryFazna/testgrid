/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.testgrid.dao.uow;

import org.wso2.testgrid.common.DeploymentPattern;
import org.wso2.testgrid.dao.EntityManagerHelper;
import org.wso2.testgrid.dao.TestGridDAOException;
import org.wso2.testgrid.dao.repository.DeploymentPatternRepository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 * This class defines the Unit of work related to a {@link DeploymentPattern}.
 *
 * @since 1.0.0
 */
public class DeploymentPatternUOW {

    private final DeploymentPatternRepository deploymentPatternRepository;

    /**
     * Constructs an instance of {@link DeploymentPatternUOW} to manager use cases related to test cases.
     */
    public DeploymentPatternUOW() {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        deploymentPatternRepository = new DeploymentPatternRepository(entityManager);
    }

    /**
     * Persists the {@link DeploymentPattern} instance.
     *
     * @param deploymentPattern    {@link DeploymentPattern} instance
     * @throws TestGridDAOException thrown when error on persisting
     */
    public DeploymentPattern persistDeploymentPattern(DeploymentPattern deploymentPattern) throws TestGridDAOException {
        // Persist deployment pattern
        return deploymentPatternRepository.persist(deploymentPattern);
    }

    /**
     * Returns the {@link DeploymentPattern} instance for the given id.
     *
     * @param id primary key of the deployment pattern
     * @return matching {@link DeploymentPattern} instance
     * @throws TestGridDAOException thrown when error on retrieving results
     */
    public Optional<DeploymentPattern> getDeploymentPatternById(String id) throws TestGridDAOException {
        DeploymentPattern deploymentPattern = deploymentPatternRepository.findByPrimaryKey(id);
        if (deploymentPattern == null) {
            return Optional.empty();
        }
        return Optional.of(deploymentPattern);
    }

    /**
     * Returns a List of {@link DeploymentPattern}.
     *
     * @return a List of {@link DeploymentPattern} for the given product name and product version
     */
    public List<DeploymentPattern> getDeploymentPatterns() throws TestGridDAOException {
        return deploymentPatternRepository.findAll();
    }

    /**
     * Returns a list of distinct {@link DeploymentPattern} instances for the given product id and date.
     *
     * @param productId associated product-id
     * @param date test ran before date
     * @return matching distinct {@link DeploymentPattern} instances
     * @throws TestGridDAOException thrown when error on retrieving results
     */
    public List<DeploymentPattern> getDeploymentPatternsByProductAndDate(String productId, long date)
            throws TestGridDAOException {
        return deploymentPatternRepository.findByProductAndDate(productId, date);
    }
}
