package com.spring_batch.customer_springbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.spring_batch.customer_springbatch.model.Customer;

import jakarta.persistence.EntityManagerFactory;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory entityManagerFactory;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager,
            EntityManagerFactory entityManagerFactory) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public FlatFileItemReader<Customer> reader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("customers.csv"));

        reader.setLinesToSkip(1);

        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer() {
            {
                setNames("id", "name", "product");
            }
        });

        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public ItemProcessor<Customer, Customer> processCustomer() {
        return new ItemProcessor<Customer, Customer>() {
            @Override
            public Customer process(Customer item) throws Exception {
                return item;
            }
        };
    }

    @Bean
    public ItemWriter<Customer> itemWriter() {
        return items -> {
            System.out.println("Writing items: " + items);
            JpaItemWriter<Customer> jpaItemWriter = new JpaItemWriter<>();
            jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
            jpaItemWriter.write(items);
        };
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .reader(reader())
                .processor(processCustomer())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job processCustomerJob() {
        return new JobBuilder("processCustomerJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }
}
