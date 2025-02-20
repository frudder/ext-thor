package com.app.thor.lang;

public interface ValueObjectWrap<T> {

  int getState();

  String getError();

  T getBody();
}