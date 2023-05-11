package com.firas.spring.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Firas Chemak
 * @since {{next_version}}
 */
@Component
public class FirstItemProcessor implements ItemProcessor<Integer,Long> {

    @Override
    public Long process(Integer item) throws Exception {
        System.out.println("Inside Item Processor");
        return Long.valueOf(item+20);
    }
}
