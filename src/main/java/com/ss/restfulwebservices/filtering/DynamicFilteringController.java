package com.ss.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DynamicFilteringController {

    //we want ot return only value1 and value2
    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue returnSomeBeen() {
        DynamicFilteringSomeBean dynamicFilteringSomeBean = new DynamicFilteringSomeBean("value1", "value2", "value3");
        //we want ot return only value1 and value2
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value2");

        //DynamicFilter is declared as filter into DynamicFilterSomeBean class
        FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(dynamicFilteringSomeBean);
        mapping.setFilters(filters);

        return mapping;
    }

    //we want ot return only value2 and value3
    @GetMapping("/dynamic-filtering-list")
    public MappingJacksonValue returnListSomeBeen() {
        List<DynamicFilteringSomeBean> list = Arrays.asList(new DynamicFilteringSomeBean("value1", "value2", "value3")
                , new DynamicFilteringSomeBean("value11", "value22", "value33"));
        //we want ot return only value2 and value3
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value2", "value3");

        //DynamicFilter is declared as filter into DynamicFilterSomeBean class
        FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);

        return mapping;
    }

//    //we want ot return only value1 and value2
//    @GetMapping("/dynamic-filtering")
//    public MappingJacksonValue returnSomeBeen() {
//        DynamicFilteringSomeBean dynamicFilteringSomeBean = new DynamicFilteringSomeBean("value1", "value2", "value3");
//        //we want ot return only value1 and value2
//        return returnMapping(new String[]{"value1", "value2"}, List.of(dynamicFilteringSomeBean));
//    }
//
//    //we want ot return only value2 and value3
//    @GetMapping("/dynamic-filtering-list")
//    public MappingJacksonValue returnListSomeBeen() {
//        List<DynamicFilteringSomeBean> list = Arrays.asList(new DynamicFilteringSomeBean("value1", "value2", "value3")
//                , new DynamicFilteringSomeBean("value11", "value22", "value33"));
//        //we want ot return only value2 and value3
//        return returnMapping(new String[]{"value2", "value3"}, list);
//    }
//
//
//    private MappingJacksonValue returnMapping(String[] valuesToStay, List<DynamicFilteringSomeBean> list) {
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(valuesToStay);
//
//        //DynamicFilter is declared as filter into DynamicFilterSomeBean class
//        FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", filter);
//
//        MappingJacksonValue mapping = new MappingJacksonValue(list);
//        mapping.setFilters(filters);
//        return mapping;
//    }
}
