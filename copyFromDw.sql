show user;
select * from global_name;
set timing on;
set serveroutput on;
whenever sqlerror exit failure rollback;
whenever oserror exit failure rollback;
select 'copy from dw start time: ' || systimestamp from dual;

prompt biodata_site
truncate table biodata_site;
insert /*+ append parallel(4) */ into biodata_site
select * from biodata_site@bioshare.er.usgs.gov;
commit;

prompt effort
truncate table effort;
insert /*+ append parallel(4) */ into effort
select * from effort@bioshare.er.usgs.gov;
commit;

prompt nawqa_study_unit
truncate table nawqa_study_unit;
insert /*+ append parallel(4) */ into nawqa_study_unit
select * from nawqa_study_unit@bioshare.er.usgs.gov;
commit;

prompt program
truncate table program;
insert /*+ append parallel(4) */ into program
select * from program@bioshare.er.usgs.gov;
commit;

prompt program_project
truncate table program_project;
insert /*+ append parallel(4) */ into program_project
select * from program_project@bioshare.er.usgs.gov;
commit;

prompt project
truncate table project;
insert /*+ append parallel(4) */ into project
select * from project@bioshare.er.usgs.gov;
commit;

prompt reach
truncate table reach;
insert /*+ append parallel(4) */ into reach
select * from reach@bioshare.er.usgs.gov;
commit;

prompt sample
truncate table sample;
insert /*+ append parallel(4) */ into sample
select * from sample@bioshare.er.usgs.gov;
commit;

prompt sample_type
truncate table sample_type;
insert /*+ append parallel(4) */ into sample_type
select * from sample_type@bioshare.er.usgs.gov;
commit;

prompt science_center
truncate table science_center;
insert /*+ append parallel(4) */ into science_center
select * from science_center@bioshare.er.usgs.gov;
commit;

prompt taxonomic_result
truncate table taxonomic_result;
insert /*+ append parallel(4) */ into taxonomic_result
select * from taxonomic_result@bioshare.er.usgs.gov;
commit;

prompt taxon_wide
truncate table taxon_wide;
insert /*+ append parallel(4) */ into taxon_wide
select * from taxon_wide@bioshare.er.usgs.gov;
commit;

select 'copy from dw end time: ' || systimestamp from dual;
