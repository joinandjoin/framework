package org.spring.boot.hxframework.autoconfigure.idgen;

import org.spring.boot.hxframework.autoconfigure.jdbc.TimeSynchronizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huaxia.framework.common.database.repository.ICommonDAO;
import com.huaxia.framework.common.idgenerator.ISequence;
import com.huaxia.framework.common.idgenerator.ITimeSynchronizer;
import com.huaxia.framework.common.idgenerator.support.Sequence;
import com.huaxia.framework.common.idgenerator.support.SequenceNumber;


@Configuration
@ConditionalOnClass({ Sequence.class,SequenceNumber.class })
@EnableConfigurationProperties(SequenceConfigProperties.class)
public class IdGeneratorAutoConfiguration {

	@Autowired
	private SequenceConfigProperties sequenceConfigProperties;
	
	@Bean
	@ConditionalOnMissingBean(name="timeSyncher")
	public  ITimeSynchronizer createTimeSyncher(ICommonDAO commonDao) {
		TimeSynchronizerImpl timeSyncher = new TimeSynchronizerImpl();
		timeSyncher.setCommonDao(commonDao);
		return timeSyncher;
	}
	
	@Bean
	@ConditionalOnMissingBean(name="idGenerator")
	@ConditionalOnProperty(prefix="spring.application.idgen",name={"datacenter","worker"})
	public  ISequence createIdGenerator(ITimeSynchronizer timeSyncher) {
		Sequence idGenerator = new Sequence(sequenceConfigProperties.getWorker(),sequenceConfigProperties.getDatacenter());
		idGenerator.setTimeSyncher(timeSyncher);
		return idGenerator;
	}
}
