package com.aleksandrakrzak.shop.repository.elastic;

import com.aleksandrakrzak.shop.domain.elastic.OrderContent;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderContentRepository extends ElasticsearchRepository<OrderContent, String> {

}
