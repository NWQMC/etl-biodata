show user;
select * from global_name;
set timing on;
set serveroutput on;
whenever sqlerror exit failure rollback;
whenever oserror exit failure rollback;
select 'transform org_data start time: ' || systimestamp from dual;

prompt building org_data_swap_biodata

prompt dropping biodata org_data indexes
exec etl_helper_org_data.drop_indexes('biodata');

set define off;
prompt populating org_data_swap_biodata
truncate table org_data_swap_biodata;
insert /*+ append parallel(4) */
  into org_data_swap_biodata (data_source_id, data_source, organization_id, organization, organization_name)
select distinct /* parallel(4) */
       data_source_id,
       data_source,
       dense_rank() over (partition by data_source_id order by organization) organization_id,
       organization,
       organization organization_name
  from station_swap_biodata
 where organization is not null;

commit;

prompt building biodata orgData indexes
exec etl_helper_org_data.create_indexes('biodata');

select 'transform orgData end time: ' || systimestamp from dual;
