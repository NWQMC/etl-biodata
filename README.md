# etl\-biodata

ETL Water Quality Data from the BioData System

These scripts are run by the OWI Jenkins Job Runners. The job name is WQP\_BIODATA\_ETL. They follow the general OWI ETL pattern using ant to control the execution of PL/SQL scripts.

The basic flow is:
* Copy data from the BioData Retrieval system into the biodata schema of the nolog database. (copyFromDW.sql)
* Drop the referential integrity constraints on the biodata swap tables of the wqp_core schema. (dropRI.sql)
* Drop the indexes on the biodata station swap table, populate with transformed data, and rebuild the indexes. (transformStation.sql)
* Drop the indexes on the biodata activity swap table, populate with transformed data, and rebuild the indexes. (transformActivity.sql)
* Drop the indexes on the biodata result swap table, populate with transformed data, and rebuild the indexes. (transformResult.sql)
* Drop the indexes on the biodata summary swap tables, populate with transformed data, and rebuild the indexes. (createSummaries.sql)
* Drop the indexes on the biodata code lookup swap tables, populate with transformed data, and rebuild the indexes. (createCodes.sql)
**Note:** Several code lookup values are dependent on data from the WQP\_NWIS\_ETL correctly collecting data from natprod.
* Add back the referential integrity constraints on the biodata swap tables of the wqp_core schema. (addRI.sql)
* Analyze the biodata swap tables of the wqp_core schema. (analyze.sql)
* Validate that rows counts and change in row counts are within the tolerated values. (validate.sql)
* Install the new data using partition exchanges. (install.sql)

The translation of data is specific to this repository. The heavy lifting (indexing, RI, partition exchanges, etc.) is done using common packages in the wqp_core schema. These are defined in the schema-wqp-core repository.