package com.producer.batch;

import com.producer.model.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job getJob(JobBuilderFactory jobBuilderFactory,
                      ItemReader<Customer> reader,
                      ItemProcessor<Customer, Customer> processor,
                      ItemWriter<Customer> writer,
                      StepBuilderFactory stepBuilderFactory) {
        Step step = stepBuilderFactory.get("Customer step")
                .<Customer, Customer>chunk(1000)
                .reader(reader)
                .writer(writer)
                .processor(processor)
                .build();

        return jobBuilderFactory.get("Customer Job").incrementer(new RunIdIncrementer()).start(step).build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> reader() {
        FlatFileItemReader<Customer> flatFileReader = new FlatFileItemReader<>();
        flatFileReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
        flatFileReader.setName("Flat File Reader");
        flatFileReader.setLineMapper(getLineMapper());
        flatFileReader.setLinesToSkip(1);
        return flatFileReader;
    }

    private LineMapper<Customer> getLineMapper() {
        DefaultLineMapper<Customer> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");
        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

}
