create unlogged table if not exists wqp.station_nwis
partition of wqp.station
for values in (2)
with (fillfactor = 100);
select create_swap_table ('biodata', 'wqp', 'org_data');
select create_swap_table ('biodata', 'wqp', 'station');
select create_swap_table ('biodata', 'wqp', 'activity');
select create_swap_table ('biodata', 'wqp', 'project_data');
select create_swap_table ('biodata', 'wqp', 'result');
