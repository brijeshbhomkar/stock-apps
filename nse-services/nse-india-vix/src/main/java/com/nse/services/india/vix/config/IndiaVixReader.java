package com.nse.services.india.vix.config;

import com.nse.services.india.vix.model.IndiaVixItem;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component("indiaVixReader")
public class IndiaVixReader extends FlatFileItemReader<IndiaVixItem> implements ItemReader<IndiaVixItem> {

    public IndiaVixReader() {
        setLinesToSkip(1);
        setLineMapper(getDefaultLineMapper());
    }

    public DefaultLineMapper<IndiaVixItem> getDefaultLineMapper() {
        DefaultLineMapper<IndiaVixItem> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[]{"Date", "Open", "High", "Low", "Close", "Prev. Close", "Change", "% Change"});
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        IndiaVixItemFieldSetMapper indiaVixItemFieldSetMapper = new IndiaVixItemFieldSetMapper();
        defaultLineMapper.setFieldSetMapper(indiaVixItemFieldSetMapper);

        return defaultLineMapper;
    }

    public class IndiaVixItemFieldSetMapper implements FieldSetMapper<IndiaVixItem> {

        @Override
        public IndiaVixItem mapFieldSet(FieldSet fieldSet) throws BindException {
            return IndiaVixItem.builder()
                    .date(fieldSet.readDate("Date", "d-MMM-yy"))
                    .open(fieldSet.readDouble("Open"))
                    .high(fieldSet.readDouble("High"))
                    .low(fieldSet.readDouble("Low"))
                    .close(fieldSet.readDouble("Close"))
                    .prevClose(fieldSet.readDouble("Prev. Close"))
                    .change(fieldSet.readDouble("Change"))
                    .perChange(fieldSet.readDouble("% Change"))
                    .build();
        }
    }
}
