package com.javacreed.api.domain.primitives.jpa.mandatory;

import java.util.UUID;

import com.javacreed.api.domain.primitives.mandatory.UuidBasedDomainObject;
import com.javacreed.api.domain.primitives.utils.UuidUtils;

public abstract class UuidBasedAttributeBytesConverter<T extends UuidBasedDomainObject>
    extends MismatchedObjectBasedAttributeConverter<UUID, T, byte[]> {

  @Override
  protected byte[] convertNotNullToDatabaseColumn(final T attribute) {
    return attribute.toBytes();
  }

  @Override
  protected UUID convertNotNullToValue(final byte[] dbData) {
    return UuidUtils.toUuid(dbData);
  }
}