package com.javacreed.api.domain.primitives.jpa.optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.javacreed.api.domain.primitives.optional.ObjectBasedDomainPrimitive;

@Converter(autoApply = true)
public abstract class MismatchedObjectBasedAttributeConverter<V, T extends ObjectBasedDomainPrimitive<V>, D>
    implements AttributeConverter<T, D> {

  protected abstract D convertNotNullToDatabaseColumn(final T value);

  protected abstract V convertNotNullToValue(D dbData);

  protected D convertNullToDatabaseColumn() {
    return null;
  }

  @Override
  public D convertToDatabaseColumn(final T attribute) {
    return attribute == null ? convertNullToDatabaseColumn() : convertNotNullToDatabaseColumn(attribute);
  }

  @Override
  public T convertToEntityAttribute(final D dbData) {
    final V value = dbData == null ? null : convertNotNullToValue(dbData);
    return createDomainPrimitive(value);
  }

  protected abstract T createDomainPrimitive(V dbData);
}
