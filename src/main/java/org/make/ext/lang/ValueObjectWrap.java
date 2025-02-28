package org.make.ext.lang;

public interface ValueObjectWrap<T> {

  int getState();

  String getError();

  T getBody();
}