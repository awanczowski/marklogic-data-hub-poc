# MarkLogic Data Hub Proof-of-Concept

Problem: Data surrounding creative, related campaigns, and analytics are held in separate systems, resources, and institutional knowledge

## Solution

To address the key problem set the MarkLogic Data Hub will be utilized to aggregate content from multiple systems. MarkLogic is an Enterprise NoSQL solution that allows users to model their domains flexibly and efficiently. The system allows for multiple schemas to be used at a single time. Additionally, individual schemas can be altered to fit your needs without needing to rebuild the entire database. The MarkLogic Data Hub framework is a quick start application to aggregate and map data. This can be leveraged to create data flows that address the key problem set. The MarkLogic Document Store, Graph Store, and Search will be utilized to meet the success criteria. Documents will be modeled in a Envelop pattern wrapping the original contents aid in the maintenance data provenance. These documents will be enriched with Semantic Triples to create a relationship graph. Finally, a search will be constructed to show the results.

## Success Criteria

* Index metadata regarding creative assets
* Index performance analytics from social networks (i.e. Facebook, Twitter, Instagram)
* Relate the assets to the analytics that are gathered from the various social networks
* Denormalize asset and campaign data into a single aggregated document.
* Search asset metadata and display all aggregate counts on a given asset for the campaign.

### Prerequisites

* Docker 3.0 or later
* Java SE JDK 8 or later
* Gradle 4.6 or later
* MarkLogic REHL 9.0-7 or later to be provided for Docker build (Download: <https://developer.marklogic.com/products>)
* MarkLogic Data Hub 5.0.0 or later to be provided for Docker build (Download: <https://github.com/marklogic/marklogic-data-hub/releases>)

### Set-up

* The application is not distributed with MarkLogic, MarkLogic Converters, or the MarkLogc Data Hub quick start. Please download and copy the files to their respective folders under `marklogic` and `data-hub-quick-start`
* Two environmental variables will need to be set to have MarkLogic start appropriately. `ML_USER` and `ML_PASS` will be used to configure the server's admin account. The admin account will be used for configuration deployment and access to the Data Hub application.
* Three entries should be added to your operating `hosts` file pointing to localhost. `datahub.local`, `grove.local` and `marklogic.local`. This is needed since the docker containers will communicate across a bridged network and reference the connection property in the gradle properties file.
* Within the data-hub solution create a gradle properties file `data-hub-config\gradle-local.properties`. This should have two props matching your env variables `mlUsername` and `mlPassword`. Do not commit this file. It is intended for local development only.  
* To generate some data for the application utilize the `ad-data-generator`. The application is pre-configured to generate content in the sample-data directory. It can be run by executing the gradle command `gradle bootRun`

### Deployment

* Within the root folder execute `docker-compose up` to build all the images and deploy.
* Access <http://marklogic.local:8001>, <http://datahub.local:8080>, and <http://grove.local:9003> to verify that all applications have started.
* For the initial Data Hub deploy execute the `gradle mlDeploy` command from the `data-hub-config` application folder.
* For the initial Search UI deploy execute the `gradle mlDeploy` command from the `search-ui` application folder.
* To generate data within the `sample-data` directory execute the `gradle bootRun` command from the `ad-data-generator`
* To load data log into the data hub and go to `Flows`, execute each Ad flow first then the Asset flow.

### Notes

* The docker configuration will run the MarkLogic Data Hub starter within a container. The container will have a shared volume within the project so configurations can be exported. This may require permissions for your Docker configuration.
