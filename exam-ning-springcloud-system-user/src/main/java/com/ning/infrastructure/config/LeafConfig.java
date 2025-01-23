package com.ning.infrastructure.config;

import com.ning.infrastructure.common.leaf.IDGen;
import com.ning.infrastructure.common.leaf.segment.SegmentIDGenImpl;
import com.ning.infrastructure.common.leaf.segment.dao.IDAllocDao;
import com.ning.infrastructure.common.leaf.segment.dao.impl.IDAllocDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LeafConfig {

    @Bean
    public IDAllocDao idAllocDao(DataSource dataSource) {
        return new IDAllocDaoImpl(dataSource);
    }

    @Bean(initMethod = "init")
    public IDGen idGen(IDAllocDao idAllocDao) {
        SegmentIDGenImpl idGen = new SegmentIDGenImpl();
        idGen.setDao(idAllocDao);
        return idGen;
    }

}
