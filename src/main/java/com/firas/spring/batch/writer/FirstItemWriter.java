package com.firas.spring.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * @author Firas Chemak
 * @since {{next_version}}
 */
@Component
public class FirstItemWriter implements ItemWriter<Long> {

    @Override
    public void write(Chunk<? extends Long> chunk) throws Exception {
        System.out.println("Inside Item Writer");
        chunk.getItems().stream().forEach(System.out::println);
    }
}
