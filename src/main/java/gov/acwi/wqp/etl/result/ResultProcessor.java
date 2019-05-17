package gov.acwi.wqp.etl.result;


import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.biodata.result.BiodataResult;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultProcessor implements ItemProcessor<BiodataResult, Result> {


    private final ConfigurationService configurationService;

    @Autowired
    public ResultProcessor(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public Result process(BiodataResult biodataResult) {
        Result result = new Result();


        return result;
    }
}
