# MarkLogic Data Hub Proof-of-Concept

Domain Problem: It is difficult to gauge performance analytics, holistically across platforms, on assets that go into a markiting campaign.

## Success Criteria

* Index metadata regarding creative assets
* Index performance analytics from social networks (i.e. Facebook, Twitter, Instagram) regarding the campaign
* Relate the assets to the analytics that are gathered from the various social networks
* Denormalize asset and campaign data into a single aggregated document.
* Show all aggregate counts on a given asset for the campaign.

## Solution

To address the key problem set the MarkLogic Data Hub will be utilized to aggretgate content from multiple systems. MarkLogic is an Enterprise NoSQL solution that allows users to model their domains flexibly and efficently. The system allows for multiple schemas to be used at a single time. Additionally, indvidual schemas can be altered to fit your needs without needing to rebuild the entire database. The MarkLogic Data Hub framework is a quick start applciation to aggregate and map data. This can be leveraged to create data flows that address the key problem set. The MarkLogic Document Store, Graph Store, and Search will be utilized to meet the success criteria. Documents will be modeled in a Envelop pattern wrapping the original contents to maintain data provinonce. These documents will be enriched with Semantic Triples to create a relationship graph. Finally, a search will be constructed to show the results.

### Prerequisets

* Docker 3.0 or later
* Java SE JDK 8 or later
* Gradle 4.6 or later
* MarkLogic REHL 9.0-7 or later to be provided for Docker build (Download: <https://developer.marklogic.com/products>)
* MarkLogic Data Hub 5.0.0 or later to be provided for Docker build (Download: <https://github.com/marklogic/marklogic-data-hub/releases>)

### Set-up

* The applciation is not distributed with MarkLogic, MarkLogic Converters, or the MarkLogc Data Hub quick start. Please download and copy the files to their respective folders under `marklogic` and `data-hub-quick-start`
* Two environmental variables will need to be set to have MarkLogic start appropriately. `ML_USER` and `ML_PASS` will be used to configure the server's admin account. The admin account will be used for configuration deployment and access to the Data Hub applciation.
* Two entireis should be added to your opperating `hosts` file pointing to localhost. `datahub.local`, `grove.local` and `marklogic.local`. This is needed since the docker containers will commnicate accross a bridged network and reference the connection property in the gradle properties file.
* Within the data-hub solution create a gradle properties file `data-hub-config\gradle-local.properties`. This should have a two props matching your env variables `mlUsername` and `mlPassword`. Do not commit this file. It is intened for local develoopment only.  
* To generarte some data for the application utilize the `ad-data-generator`. The application is pre-conifgured to generate content in the sample-data directory. It can be run by executing the gradle command `gradle bootRun`

### Deployment

* Within the root folder execute `docker-compose up` to build all the images and deploy.
* Access <http://localhost:8001>, <http://localhost:8080>, and <http://localhost:9003> to verify that MarkLogic and the Data Hub have started.
* For the intial Data Hub deploy execute the `gradle mlDeploy` command from the `data-hub-config` applciation folder.
* For the intial Search UI deploy execute the `gradle mlDeploy` command from the `search-ui` applciation folder.
* Access the Search UI  by reaching <http://grove.local:9003>
* Access the Data Hub by reaching <http://datahub.local:8080>
* Access the Marklogic admin by reaching <http://marklogic.local:8001>

### Notes

* The docker configuration will run the MarkLogic DataHub starter within a container. The container will have a shared volume within the project so configurations can be exported. This may require permissions for your Docker configuration.
