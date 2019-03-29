package gov.acwi.wqp.etl;

import org.springframework.context.annotation.Import;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@Import({DBTestConfig.class, BiodataDBTestConfig.class})
@DbUnitConfiguration(databaseConnection={"wqp","pg","biodata"})
public abstract class BiodataBaseFlowIT extends BaseFlowIT{
}
