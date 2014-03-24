/***************************************************************************
 * Copyright 2013 Kieker Project (http://kieker-monitoring.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.common.record.flow.trace.operation.object;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import kieker.common.record.IMonitoringRecord;
import kieker.common.util.registry.IRegistry;

import kieker.common.record.flow.trace.operation.AfterOperationEvent;
import kieker.common.record.flow.IObjectRecord;

/**
 * @author Generic Kieker
 * 
 * @since 1.10
 */
public class AfterOperationObjectEvent extends AfterOperationEvent implements IMonitoringRecord.Factory, IMonitoringRecord.BinaryFactory, IObjectRecord {
	public static final int SIZE = 32; // serialization size (without variable part of strings)
	private static final long serialVersionUID = 1395333732132L;
	
	private static final Class<?>[] TYPES = {
		long.class, // AbstractEvent.timestamp
		long.class, // ITraceRecord.traceId
		int.class, // ITraceRecord.orderIndex
		String.class, // IOperationRecord.classSignature
		String.class, // IOperationRecord.operationSignature
		int.class, // IObjectRecord.objectId
	};
	
	
	private final int objectId;

	/**
	 * Creates a new instance of this class using the given parameters.
	 * 
	 * @param timestamp
	 *            timestamp
	 * @param traceId
	 *            traceId
	 * @param orderIndex
	 *            orderIndex
	 * @param classSignature
	 *            classSignature
	 * @param operationSignature
	 *            operationSignature
	 * @param objectId
	 *            objectId
	 */
	public AfterOperationObjectEvent(final long timestamp, final long traceId, final int orderIndex, final String classSignature, final String operationSignature, final int objectId) {
		super(timestamp, traceId, orderIndex, classSignature, operationSignature);
		this.objectId = objectId;
	}

	/**
	 * This constructor converts the given array into a record. It is recommended to use the array which is the result of a call to {@link #toArray()}.
	 * 
	 * @param values
	 *            The values for the record.
	 */
	public AfterOperationObjectEvent(final Object[] values) { // NOPMD (direct store of values)
		super(values, TYPES);
		this.objectId = (int) values[5];
	}
	
	/**
	 * This constructor uses the given array to initialize the fields of this record.
	 * 
	 * @param values
	 *            The values for the record.
	 * @param valueTypes
	 *            The types of the elements in the first array.
	 */
	protected AfterOperationObjectEvent(final Object[] values, final Class<?>[] valueTypes) { // NOPMD (values stored directly)
		super(values, valueTypes);
		this.objectId = (int) values[5];
	}

	/**
	 * This constructor converts the given array into a record.
	 * 
	 * @param buffer
	 *            The bytes for the record.
	 * 
	 * @throws BufferUnderflowException
	 *             if buffer not sufficient
	 */
	public AfterOperationObjectEvent(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferUnderflowException {
		super(buffer, stringRegistry);
		this.objectId = buffer.getInt();
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] toArray() {
		return new Object[] {
			this.getTimestamp(),
			this.getTraceId(),
			this.getOrderIndex(),
			this.getClassSignature(),
			this.getOperationSignature(),
			this.getObjectId()
		};
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeBytes(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferOverflowException {
		buffer.putLong(this.getTimestamp());
		buffer.putLong(this.getTraceId());
		buffer.putInt(this.getOrderIndex());
		buffer.putInt(stringRegistry.get(this.getClassSignature()));
		buffer.putInt(stringRegistry.get(this.getOperationSignature()));
		buffer.putInt(this.getObjectId());
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<?>[] getValueTypes() {
		return TYPES; // NOPMD
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSize() {
		return SIZE;
	}
	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated This record uses the {@link kieker.common.record.IMonitoringRecord.Factory} mechanism. Hence, this method is not implemented.
	 */
	@Deprecated
	public void initFromArray(final Object[] values) {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated This record uses the {@link kieker.common.record.IMonitoringRecord.BinaryFactory} mechanism. Hence, this method is not implemented.
	 */
	@Deprecated
	public void initFromBytes(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferUnderflowException {
		throw new UnsupportedOperationException();
	}

	public final int getObjectId() {
		return this.objectId;
	}
	
}