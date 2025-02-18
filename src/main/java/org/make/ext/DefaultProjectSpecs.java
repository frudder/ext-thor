package org.make.ext;

public enum DefaultProjectSpecs {

    DEFAULT_CONTROLLER_NAME("ThorRouter"),
    DEFAULT_ENTITY_NAME("ThorEntity"),
    DEFAULT_SERVICE_NAME("ThorTrait"),
    DEFAULT_DOMAIN_NAME("ThorDomain"),
    DEFAULT_MAPPER_NAME("MapperAdapter"),
    DEFAULT_ENTITY_SUFFIX("Entity"),

    ;
    private final String name;

    DefaultProjectSpecs(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
