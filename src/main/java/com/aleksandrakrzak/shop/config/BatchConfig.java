package com.aleksandrakrzak.shop.config;

import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.domain.dto.ProductDto;
import com.aleksandrakrzak.shop.processor.ProductProcessorImpl;
import com.aleksandrakrzak.shop.processor.ProductReaderImpl;
import com.aleksandrakrzak.shop.processor.ProductWriterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final ProductProcessorImpl productProcessor;

    private final ProductReaderImpl productReader;

    private final ProductWriterImpl productWriter;

    private final JobBuilderFactory jobBuilderFactory; // do tworzenia batchowego joba - po to aby moc uruchomic przetwarzeni pliku w batchu

    private final StepBuilderFactory stepBuilderFactory; // do rejestracji readera, processora i writera

    private TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor productBatchProcessing = new SimpleAsyncTaskExecutor("product batch processing"); // SimpleAsyncTaskExecutor jest wyokrzystywana do przetwarzania wielowatkowego w batch
        productBatchProcessing.setConcurrencyLimit(10); // ustawiamy ilosc watkow
        return productBatchProcessing;
    }

    private Step stepProductFileDefinition(String fileName) {
        return stepBuilderFactory.get("processing a csv file with product")
                .<ProductDto, Product>chunk(10) // chunk to ilosc rekordow jedoczesnie przetwarzana przez jeden watek; dodajemy paramteryzacje bo funkcja chank jest  paramteryzowana
                .reader(productReader.reader(fileName))
                .processor(productProcessor)
                .writer(productWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    public Job jobProductFileDefinition(String fileName) { // job - praca jaka musi wykoanc program
        return jobBuilderFactory.get("processing a csv file with product")
                .incrementer(new RunIdIncrementer()) //  kazdemu jobowi musimy nadac id
                .flow(stepProductFileDefinition(fileName)) // wrzucamy stepa  wktorym mam readera processora i writera
                .end()
                .build();
    }

}
