package com.aleksandrakrzak.shop.domain.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "order_content") //dokument w easticserachu tzw index
public class OrderContent {

    @Id
    private String id;
    @Field(type = FieldType.Keyword) // pole w elasticu, po tym polu bedziemy wyszukiwac
    private String orderNumber;
    private String productName;
    private String productDescription;
    private Double price;
    private Double quantity;

}
