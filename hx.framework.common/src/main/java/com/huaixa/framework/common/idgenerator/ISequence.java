package com.huaixa.framework.common.idgenerator;

public interface ISequence {

	/**
	 * 以SequenceNumber的形式返回新的序列值
	 * 
	 * @return SequenceNumber 新序列值的SequenceNumber表示
	 */
	ISequenceNumber nextSequence();

	/**
	 * 以long的形式返回序列值
	 * @return long 新的序列值
	 */
	long nextId();

	void setTimeSyncher(ITimeSynchronizer timeSyncher);
}