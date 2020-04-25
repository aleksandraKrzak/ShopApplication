package com.aleksandrakrzak.shop.service.impl;

import com.aleksandrakrzak.shop.config.BatchConfig;
import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.repository.jpa.ProductRepository;
import com.aleksandrakrzak.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@Slf4j // dodanie zmiennej do loggerow
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final BatchConfig batchConfig;

    private final JobLauncher jobLauncher;

    private final ProductRepository productRepository; // po to aby miec dosotep do bazy danych

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) { // pdate tozmiana danych w bazie danych
        //1. mapujeesz z productdto na product
        //2. sprawdzic czy pola/atrybty prodct sie zmienily, jezeli sie zmienily to zapisje do bazy danych

        Optional<Product> optionalProductDB = productRepository.findById(product.getId()); // szukam produkt w bazie danych ktory chce zaktualizowac

        //optional opakowywuje inne obiekty zeby uniknac nullpointerexception
        //optionalProductDB musimy zmapowac na productDTO

        return optionalProductDB.map(productDB -> {
            if (product.getName() != null && !productDB.getName().equals(product.getName())) {
                productDB.setName(product.getName());
            }

            if (product.getDescription() != null && !productDB.getDescription().equals(product.getDescription())) {
                productDB.setDescription(product.getDescription());
            }

            if (product.getQuantity() != null && !productDB.getQuantity().equals(product.getQuantity())) {
                productDB.setQuantity(product.getQuantity());
            }

            if (product.getPrice() != null && productDB.getPrice().equals(product.getPrice())) {
                productDB.setPrice(product.getPrice());
            }

            //zapisjemy productDB do bazy danych i mapuejmy na productDTO
            return productRepository.save(productDB);
        }).orElseThrow(() -> new EntityNotFoundException("Product with id:" + product.getId() + " does not exist"));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> loadPage(Pageable pageable) {
        // 1. pobieraenie strony z bazy danych
        return productRepository.findAll(pageable);  // strona z produktami

        //2. musze zmapowac z pageproduct na pageproductdto
//        return productMapper.pageProductToPageProductDTO(all);
    }

    @Override
    public void processFile(MultipartFile multipartFile) {
        JobParameters jobID = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        Path path = Paths.get("C:\\Users\\Jane\\assets", multipartFile.getOriginalFilename());

        try {
            multipartFile.transferTo(new File(path.toString())); // Paths klasa funkcja get()
            jobLauncher.run(batchConfig.jobProductFileDefinition(path.toString()), jobID);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException | IOException | JobInstanceAlreadyCompleteException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Product getById(Long id) {
        log.info("product with id {} does not exist", id);

        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("product with " + id + " does not exist"));
    }

}
