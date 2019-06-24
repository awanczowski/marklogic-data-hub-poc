# MarkLogic Data Hub Proof-of-Concept

Domain Problem: It is difficult to gauge performance analytics on indvidual assets that go into a markiting campaign.

## Success Criteria

* Index metadata regarding indvidual creative assets
* Relate the assets to the composition that is used for markiting collatoral
* Index performance analytics from social networks (i.e. Facebook, Twitter, Instagram) regarding the campaign
* Show all campaigns asset appeard in with the aggregate counts of analytics

## Solution

To address the key problem set the MarkLogic Data Hub will be utilized to aggretgate content from multiple systems. MarkLogic is an Enterprise NoSQL solution that allows users to model their domains flexibly and efficently. The system allows for multiple schemas to be used at a single time. Additionally, indvidual schemas can be altered to fit your needs without needing to rebuild the entire database. The MarkLogic Data Hub framework is a quick start applciation to aggregate and map data. This can be leveraged to create data flows that address the key problem set. The MarkLogic Document Store, Graph Store, and Search will be utilized to meet the success criteria. Documents will be modeled in a Envelop pattern wrapping the original contents to maintain data provinonce. These documents will be enriched with Semantic Triples to create a relationship graph. Finally, a search will be constructed to show the results.

### Prerequisets

* Docker 3.0 or later
* Java SE JDK 8 or later
* Gradle 4.6 or later
* MarkLogic REHL 9.0-7 or later to be provided for Docker build (Download: <https://developer.marklogic.com/products>)
* MarkLogic Data Hub 5.0.0 or later to be provided for Docker build (Download: <https://github.com/marklogic/marklogic-data-hub/releases>)

### Set-up

* The applciation is not distributed with MarkLogic, MarkLogic Converters, or the MarkLogc Data Hub quick start. Please download and copy the files to their respective folders under `docker`
* Two environmental variables will need to be set to have MarkLogic start appropriately. `ML_USER` and `ML_PASS` will be used to configure the server's admin account. The admin account will be used for configuration deployment and access to the Data Hub applciation.
* Two entireis should be added to your opperating `hosts` file pointing to localhost. `datahub.local` and `marklogic.local`. This is needed since the docker containers will commnicate accross a bridged network and reference the connection property in the gradle properties file. 
* Within the data-hub solution create a gradle properties file `data-hub\gradle-local.properties`. This should have a two props matching your env variables `mlUsername` and `mlPassword`. Do not commit this file. It is intened for local develoopment only.  

### Deployment

* Within the `docker` folder execute `docker-compose up` to build the images and deploy.
* Access <http://localhost:8001> and <http://localhost:8080> to verify that MarkLogic and the Data Hub have started.
* For the intial deploy execute the `gradle mlDeploy` command from the `data-hub` applciation folder.
* Access the application by reaching <http://datahub.local:8080>
* Access the Marklogic admin by reaching <http://marklogic.local:8001>

### Notes

* The docker configuration will run the MarkLogic DataHub starter within a container. The container will have a shared volume within the project so configurations can be exported. This may require permissions for your Docker configuration.
