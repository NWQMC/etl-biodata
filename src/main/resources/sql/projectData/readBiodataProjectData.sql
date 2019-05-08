select distinct
    activity.data_source_id
    ,project.dw_project_id -- PROJECT_DATA table project_id
    ,activity.data_source
    ,activity.organization
    ,activity.organization_name
    ,activity.project_id -- PROJECT_DATA table project_identifier
    ,activity.project_name
    ,project.project_abstract -- PROJECT_DATA table description

from biodata.project
	join activity_swap_biodata activity
        on project.project_label = activity.project_id

where
    project.data_release_category = 'Public';