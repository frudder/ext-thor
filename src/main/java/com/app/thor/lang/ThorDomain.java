package com.app.thor.lang;

import java.io.Serializable;

public class ThorDomain<T extends ThorEntity<? extends Serializable>, R extends MapperAdapter<T>> implements ThorTrait<T, R> {


}
