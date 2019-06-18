drop table if exists wqp.station_nwis cascade;
create unlogged table if not exists wqp.station_nwis
partition of wqp.station
for values in (2)
with (fillfactor = 100);

drop table if exists wqp.station_swap_biodata cascade;
select create_swap_table ('biodata', 'wqp', 'station');

drop table if exists wqp.org_data_swap_biodata cascade;
select create_swap_table ('biodata', 'wqp', 'org_data');

drop table if exists wqp.activity_swap_biodata cascade;
select create_swap_table ('biodata', 'wqp', 'activity');

drop table if exists wqp.project_data_swap_biodata cascade;
select create_swap_table ('biodata', 'wqp', 'project_data');

drop table if exists wqp.result_swap_biodata cascade;
select create_swap_table ('biodata', 'wqp', 'result');
